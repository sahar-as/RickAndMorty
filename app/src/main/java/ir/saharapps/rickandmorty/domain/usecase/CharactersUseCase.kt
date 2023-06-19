package ir.saharapps.rickandmorty.domain.usecase

import androidx.core.text.isDigitsOnly
import ir.saharapps.rickandmorty.data.local.CharacterEntity
import ir.saharapps.rickandmorty.data.repository.CharacterLocalRepositoryImpl
import ir.saharapps.rickandmorty.data.repository.CharactersRemoteRepositoryImpl
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val characterRemoteRepository: CharactersRemoteRepositoryImpl,
    private val characterLocalRepositoryImpl: CharacterLocalRepositoryImpl
){
    suspend fun getAllCharacters(): List<Character>{

        var localCharacters = characterLocalRepositoryImpl.getCharacters()
        val characterList = mutableListOf<Character>()

        for(item in localCharacters){
            characterList.add(Character(item.id, item.name, item.image))
        }

        return characterList
    }

    suspend fun getCharacterById(id: Int): DetailCharacter {

        val repositoryResult = characterRemoteRepository.getCharacterById(id)

        return DetailCharacter(
            repositoryResult.id, repositoryResult.name,
            repositoryResult.image, repositoryResult.status, repositoryResult.species,
            repositoryResult.gender, repositoryResult.episode
        )
    }

    suspend fun getEpisodeList(id: Int): List<Episode>{

        val repositoryResult = characterRemoteRepository.getCharacterById(id)
        val episodes = mutableListOf<Episode>()
        for(episode in repositoryResult.episode){
            val id = episode.split("episode/").lastOrNull()
            if(!id.isNullOrEmpty() && id.isDigitsOnly()){
                episodes.add(Episode(id.toInt(), episode))
            }
        }
        return episodes
    }

    suspend fun fetchRemoteCharacters(): List<Character>{

        val remoteCharacters = characterRemoteRepository.getCharacters()
            if(remoteCharacters.isNotEmpty()){
                saveCharacterToDB(remoteCharacters)
            }
        return getAllCharacters()
    }

    private suspend fun saveCharacterToDB(characters: List<CharacterEntity>){

        for(character in characters){
            characterLocalRepositoryImpl.addCharacter(character)
        }
    }
}