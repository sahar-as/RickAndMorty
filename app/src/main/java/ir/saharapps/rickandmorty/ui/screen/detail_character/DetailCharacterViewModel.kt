package ir.saharapps.rickandmorty.ui.screen.detail_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.DetailCharacterViewState
import ir.saharapps.rickandmorty.domain.model.ViewState
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow(DetailCharacterViewState())
    val viewState: StateFlow<DetailCharacterViewState> = _viewState.asStateFlow()

    fun getCharacterDetail(characterId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = DetailCharacterViewState().update { copy(viewState = ViewState.LOADING) }
            try {
                val characterInfo = useCase.getCharacterById(characterId)
                val episode = useCase.getEpisodeList(characterId)
                _viewState.value = DetailCharacterViewState().update {
                    copy(viewState = ViewState.SUCCESS, detailCharacter = characterInfo, episodeList = episode)
                }
            }catch (e: Exception){
                _viewState.value = DetailCharacterViewState().update { copy(viewState = ViewState.FAILED) }
            }
        }
    }
}

fun <T> T.update(newState: T.() -> T): T{
    return newState()
}

//fun <T> T.updateState(newState: T.() -> T) {
//    val value = newState
//}
