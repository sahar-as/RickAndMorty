package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Character

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}