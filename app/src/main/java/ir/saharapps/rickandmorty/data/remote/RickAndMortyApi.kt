package ir.saharapps.rickandmorty.data.remote

import ir.saharapps.rickandmorty.data.dto.CharacterApiResult
import ir.saharapps.rickandmorty.data.dto.CharacterDto
import ir.saharapps.rickandmorty.utils.APIs
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET(APIs.CHARACTERS)
    suspend fun getCharacters(): CharacterApiResult

    @GET(APIs.CHARACTERS + "{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto

}