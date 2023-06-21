package ir.saharapps.rickandmorty.domain.model

data class Character (
    val id: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean,
    val favTextColor: Int,
    val favBackground: Int,
    val favText: Int
)