package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Result

interface RickMortyRepository {

    suspend fun getCharacters(): List<Result>
}