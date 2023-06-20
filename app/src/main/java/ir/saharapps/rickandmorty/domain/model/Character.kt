package ir.saharapps.rickandmorty.domain.model

data class Character (
    val id: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean
)