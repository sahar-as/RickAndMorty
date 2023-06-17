package ir.saharapps.rickandmorty.domain.usecase

import ir.saharapps.rickandmorty.data.repository.CharactersRemoteRepositoryImpl
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val characterRemoteRepository: CharactersRemoteRepositoryImpl
){
    suspend fun getAllCharacters(): List<Character>{
        val repositoryResult = characterRemoteRepository.getCharacters()
        val characterList = mutableListOf<Character>()
        for(item in repositoryResult){
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
            val id = episode.split("episode/").getOrElse(1){""}
            episodes.add(Episode(id.toInt(), episode))
        }
        return episodes
    }
}