package ir.saharapps.rickandmorty.data.remote

import ir.saharapps.rickandmorty.data.dto.CharacterApiResult
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacter(): CharacterApiResult

}