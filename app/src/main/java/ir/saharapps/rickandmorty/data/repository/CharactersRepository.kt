package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Characters

interface CharactersRepository {

    suspend fun getCharacters(): List<Characters>
    suspend fun getCharacterById(id: Int): Characters
}