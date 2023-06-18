package ir.saharapps.rickandmorty.domain.model


data class DetailCharacterViewState(
    val viewState: ViewState = ViewState.INITIAL,
    val detailCharacter: DetailCharacter? = null,
    val episodeList: List<Episode> = emptyList()
)

