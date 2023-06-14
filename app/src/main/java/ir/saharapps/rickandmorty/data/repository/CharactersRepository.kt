package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Result

interface CharactersRepository {

    suspend fun getCharacters(): List<Result>
}