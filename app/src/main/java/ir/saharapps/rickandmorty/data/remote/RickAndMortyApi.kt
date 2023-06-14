package ir.saharapps.rickandmorty.data.remote

import ir.saharapps.rickandmorty.data.dto.CharacterApiResult
import ir.saharapps.rickandmorty.utils.APIs
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET(APIs.CHARACTERS)
    suspend fun getCharacter(): CharacterApiResult

}