package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.local.CharacterEntity

interface CharactersRepository {

    suspend fun getCharacters(pageNumber: Int): List<CharacterEntity>
    suspend fun getCharacterById(id: Int): CharacterEntity
}