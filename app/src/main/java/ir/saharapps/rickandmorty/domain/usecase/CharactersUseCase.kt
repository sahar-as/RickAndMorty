package ir.saharapps.rickandmorty.domain.usecase

import androidx.core.text.isDigitsOnly
import ir.saharapps.rickandmorty.data.repository.CharacterLocalRepositoryImpl
import ir.saharapps.rickandmorty.data.repository.CharactersRemoteRepositoryImpl
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import ir.saharapps.rickandmorty.domain.utility.toCharacterUi
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val characterRemoteRepository: CharactersRemoteRepositoryImpl,
    private val characterLocalRepository: CharacterLocalRepositoryImpl
){
    suspend fun getAllCharacters(pageNumber: Int ,onDataReceive: (characters: List<Character>) -> Unit){

        onDataReceive(getLocalCharacters(pageNumber))

        getRemoteCharacter(pageNumber)

        onDataReceive(getLocalCharacters(pageNumber))

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

    private suspend fun getLocalCharacters(pageNumber: Int): List<Character>{
        var localCharacters = characterLocalRepository.getCharacters(pageNumber)

        return localCharacters.map { localCharacter ->  localCharacter.toCharacterUi() }
    }

    private suspend fun getRemoteCharacter(pageNumber: Int){
        val remoteCharacters = characterRemoteRepository.getCharacters(pageNumber)
        for(character in remoteCharacters){
            characterLocalRepository.addCharacter(character)
        }
    }
}