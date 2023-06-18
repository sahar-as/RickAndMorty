package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.local.CharacterEntity
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import ir.saharapps.rickandmorty.data.utility.convertToCharacterEntity
import javax.inject.Inject

class CharactersRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
): CharactersRepository {

    override suspend fun getCharacters(): List<CharacterEntity> {
        val characterDtoList = api.getCharacters().character
        return characterDtoList.map { it.convertToCharacterEntity() }
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
        val characterDto = api.getCharacterById(id)
        return characterDto.convertToCharacterEntity()
    }
}