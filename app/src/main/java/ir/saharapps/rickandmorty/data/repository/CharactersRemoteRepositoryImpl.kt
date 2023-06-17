package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.Character
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import javax.inject.Inject

class CharactersRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
): CharactersRepository {

    override suspend fun getCharacters(): List<Character> {
        val characterList = api.getCharacters().characters
//        for(character in characterList){
//            database.characterDao().addCharacter(character)
//        }
        return characterList
    }

    override suspend fun getCharacterById(id: Int): Character {
        return api.getCharacterById(id)
    }
}