package ir.saharapps.rickandmorty.domain.usecase

import ir.saharapps.rickandmorty.data.repository.RickMortyRemoteRepositoryImpl
import ir.saharapps.rickandmorty.domain.model.Character
import javax.inject.Inject

class RickMortyRemoteUseCase @Inject constructor(
    private val repositoryImpl: RickMortyRemoteRepositoryImpl
){
    suspend fun getAllCharacters(): List<Character>{
        val repositoryResult = repositoryImpl.getCharacters()
        val characterList = mutableListOf<Character>()
        for(item in repositoryResult){
            characterList.add(Character(item.id, item.name, item.image))
        }
        return characterList
    }
}