package ir.saharapps.rickandmorty.data.utility

import ir.saharapps.rickandmorty.data.dto.CharacterDto
import ir.saharapps.rickandmorty.data.local.CharacterEntity

fun CharacterDto.toCharacterEntity(): CharacterEntity{
    return CharacterEntity(
        id = id,
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        locationName = location.name,
        locationUrl = location.url,
        name = name,
        species = species,
        status = status,
        type = type,
        url = url,
        isFavorite = false
    )
}