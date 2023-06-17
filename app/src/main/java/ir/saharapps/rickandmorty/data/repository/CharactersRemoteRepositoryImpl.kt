package ir.saharapps.rickandmorty.data.repository

import ir.saharapps.rickandmorty.data.dto.CharacterDto
import ir.saharapps.rickandmorty.data.local.CharacterEntity
import ir.saharapps.rickandmorty.data.remote.RickAndMortyApi
import javax.inject.Inject

class CharactersRemoteRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
): CharactersRepository {

    override suspend fun getCharacters(): List<CharacterEntity> {
        val characterDtoList = api.getCharacters().character
        val characterDataList = convertCharacterDataClass(characterDtoList)

//        for(character in characterList){
//            database.characterDao().addCharacter(character)
//        }
        return characterDataList
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
        val characterDto = api.getCharacterById(id)
        return convertCharacterDataClass(characterDto)
    }

    private fun convertCharacterDataClass(characterDto: List<CharacterDto>): List<CharacterEntity>{
        return characterDto.map { characterDto ->
            CharacterEntity(
                id = characterDto.id,
                created = characterDto.created,
                episode = characterDto.episode,
                gender = characterDto.gender,
                image = characterDto.image,
                locationName = characterDto.location.name,
                locationUrl = characterDto.location.url,
                name = characterDto.name,
                species = characterDto.species,
                status = characterDto.status,
                type = characterDto.type,
                url = characterDto.url
            )
        }
    }
    private fun convertCharacterDataClass(characterDto: CharacterDto): CharacterEntity{
        return CharacterEntity(
                id = characterDto.id,
                created = characterDto.created,
                episode = characterDto.episode,
                gender = characterDto.gender,
                image = characterDto.image,
                locationName = characterDto.location.name,
                locationUrl = characterDto.location.url,
                name = characterDto.name,
                species = characterDto.species,
                status = characterDto.status,
                type = characterDto.type,
                url = characterDto.url
            )
    }
}