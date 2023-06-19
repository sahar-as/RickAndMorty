package ir.saharapps.rickandmorty.domain.utility

import ir.saharapps.rickandmorty.data.local.CharacterEntity
import ir.saharapps.rickandmorty.domain.model.Character

fun CharacterEntity.convertCharacterEntityToCharacter(): Character {
    return Character(
        id = id,
        name = name,
        image = image
    )
}