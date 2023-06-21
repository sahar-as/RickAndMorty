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
        favTextColor  = if(isFavorite) R.color.white else R.color.black,
        favBackground = if (isFavorite)  R.drawable.round_dark_purple_background else R.drawable.round_light_purple_background,
        favText = if (isFavorite) R.string.remove_favorite else R.string.add_favorite
    )
}