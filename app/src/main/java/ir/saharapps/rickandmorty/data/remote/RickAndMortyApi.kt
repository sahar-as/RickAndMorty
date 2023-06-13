package ir.saharapps.rickandmorty.data.remote

import ir.saharapps.rickandmorty.data.dto.RickAndMortyDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacter(): RickAndMortyDto
}