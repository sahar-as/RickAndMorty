package ir.saharapps.rickandmorty.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.CharacterViewState
import ir.saharapps.rickandmorty.domain.model.ViewState
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import ir.saharapps.rickandmorty.ui.screen.utility.updateState
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
            _viewState.updateState { copy(viewState = ViewState.LOADING) }
            try {
                val characters = characterUseCase.getAllCharacters()
                _viewState.updateState {
                    copy(viewState = ViewState.SUCCESS, characters = characters)
                }
            }catch (e: Exception){
                _viewState.updateState{copy(viewState = ViewState.FAILED)}
            }
        }
    }

}

