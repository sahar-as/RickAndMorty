package ir.saharapps.rickandmorty.domain.usecase

import androidx.core.text.isDigitsOnly
import ir.saharapps.rickandmorty.data.repository.CharacterLocalRepositoryImpl
import ir.saharapps.rickandmorty.data.repository.CharactersRemoteRepositoryImpl
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import ir.saharapps.rickandmorty.domain.utility.convertToCharacter
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val characterRemoteRepository: CharactersRemoteRepositoryImpl,
    private val characterLocalRepository: CharacterLocalRepositoryImpl
){
    suspend fun getAllCharacters(onDataReceive: (characters: List<Character>) -> Unit){

        onDataReceive(getLocalCharacters())

        getRemoteCharacter()

        onDataReceive(getLocalCharacters())

    }

    suspend fun getCharacterById(id: Int): DetailCharacter {

        val repositoryResult = characterLocalRepository.getCharacterById(id)

        return DetailCharacter(
            repositoryResult.id, repositoryResult.name,
            repositoryResult.image, repositoryResult.status, repositoryResult.species,
            repositoryResult.gender, repositoryResult.episode
        )
    }

    suspend fun getEpisodeList(id: Int): List<Episode>{

        val localCharacter = characterLocalRepository.getCharacterById(id)

        val episodes = mutableListOf<Episode>()
        for(episode in localCharacter.episode){
            val id = episode.split("episode/").lastOrNull()
            if(!id.isNullOrEmpty() && id.isDigitsOnly()){
                episodes.add(Episode(id.toInt(), episode))
            }
        }

        return episodes
    }

    private suspend fun getLocalCharacters(): List<Character>{
        var localCharacters = characterLocalRepository.getCharacters()

        return localCharacters.map { it.convertToCharacter() }
    }

    private suspend fun getRemoteCharacter(){
        val remoteCharacters = characterRemoteRepository.getCharacters()
        for(character in remoteCharacters){
            characterLocalRepository.addCharacter(character)
        }
    }
}