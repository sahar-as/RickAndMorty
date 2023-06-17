package ir.saharapps.rickandmorty.domain.model


data class DetailCharacterViewState(
    val viewState: ViewState = ViewState.INITIAL,
    val detailCharacter: DetailCharacter = DetailCharacter(1,"","","","","", emptyList()),
    val episodeList: List<Episode> = emptyList()
)

