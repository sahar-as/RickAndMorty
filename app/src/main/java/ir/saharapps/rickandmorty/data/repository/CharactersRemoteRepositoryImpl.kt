package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Result
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import javax.inject.Inject

class CharactersRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
): CharactersRepository {

    override suspend fun getCharacters(): List<Result> {
        return api.getCharacter().results
    }
}