package ir.saharapps.rickandmorty.ui.screen.characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.CharacterViewState
import ir.saharapps.rickandmorty.domain.model.ViewState
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharactersUseCase
): ViewModel() {

    private val _viewState = MutableStateFlow(CharacterViewState())
    val viewState: StateFlow<CharacterViewState> = _viewState.asStateFlow()
    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = CharacterViewState().update { copy(viewState = ViewState.LOADING) }
            try {
                val characters = characterUseCase.getAllCharacters()
                _viewState.value = _viewState.value.update {
                    copy(viewState = ViewState.SUCCESS, characters = characters)
                }
            }catch (e: Exception){
                _viewState.value = _viewState.value.update{copy(viewState = ViewState.FAILED)}
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

