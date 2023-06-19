package ir.saharapps.rickandmorty.data.remote

import ir.saharapps.rickandmorty.data.dto.CharacterApiResult
import ir.saharapps.rickandmorty.data.dto.CharacterDto
import ir.saharapps.rickandmorty.data.utility.APIs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET(APIs.CHARACTERS)
    suspend fun getCharacters(@Query("page") pag: Int): CharacterApiResult

    @GET(APIs.CHARACTERS + "{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto

}