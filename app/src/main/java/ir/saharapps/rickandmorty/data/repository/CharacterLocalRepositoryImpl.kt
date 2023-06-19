package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.local.CharacterDatabase
import ir.saharapps.rickandmorty.data.local.CharacterEntity
import javax.inject.Inject

class CharacterLocalRepositoryImpl @Inject constructor(
    private val database: CharacterDatabase
): CharactersRepository {

    private val characterDao = database.characterDao()

    override suspend fun getCharacters(pageNumber: Int): List<CharacterEntity> {
        return characterDao.getAllCharacters()
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
        return characterDao.getCharacterById(id)
    }

    suspend fun addCharacter(characterEntity: CharacterEntity){
        characterDao.addCharacter(characterEntity)
    }
}