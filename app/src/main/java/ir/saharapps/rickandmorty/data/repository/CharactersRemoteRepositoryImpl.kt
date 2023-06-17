package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Characters
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import javax.inject.Inject

class CharactersRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
): CharactersRepository {

    override suspend fun getCharacters(): List<Characters> {
        return api.getCharacters().characters
    }

    override suspend fun getCharacterById(id: Int): Characters {
        return api.getCharacterById(id)
    }
}