package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.CharacterApiResult
import ir.saharapps.rickandmorty.data.dto.Result
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import javax.inject.Inject

class RickMortyRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
): RickMortyRepository {

    override suspend fun getCharacters(): List<Result> {
        return api.getCharacter().results
    }
}