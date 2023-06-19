package ir.saharapps.rickandmorty.domain.model


data class CharacterViewState(
    val viewState: ViewState = ViewState.INITIAL,
    val pageNumber: Int = 1,
    val characters: List<Character> = emptyList()
)

enum class ViewState{
    INITIAL,LOADING, SUCCESS, FAILED
}
