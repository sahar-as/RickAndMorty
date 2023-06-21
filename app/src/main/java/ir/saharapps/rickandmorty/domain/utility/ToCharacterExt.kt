package ir.saharapps.rickandmorty.domain.utility

import android.graphics.Color
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.data.local.CharacterEntity
import ir.saharapps.rickandmorty.domain.model.Character

fun CharacterEntity.toCharacterUi(): Character {
    return Character(
        id = id,
        name = name,
        image = image,
        isFavorite = isFavorite,
        favTextColor  = if(isFavorite) Color.WHITE else Color.BLACK,
        favBackground = if (isFavorite)  R.drawable.round_dark_purple_background else R.drawable.round_light_purple_background,
        favText = if (isFavorite) "Remove From favorite" else "Add to favorite"
    )
}