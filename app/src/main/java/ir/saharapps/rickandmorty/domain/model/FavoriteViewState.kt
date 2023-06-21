package ir.saharapps.rickandmorty.domain.model

data class FavoriteViewState(
    val emptyFavList: Boolean = false,
    val characters: List<Character> = emptyList()
)