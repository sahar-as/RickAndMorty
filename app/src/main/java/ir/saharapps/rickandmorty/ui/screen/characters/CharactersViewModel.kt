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

    private val _viewStateFlow = MutableStateFlow(CharacterViewState())
    val viewStateFlow: StateFlow<CharacterViewState> = _viewStateFlow.asStateFlow()

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {

            _viewStateFlow.updateState { copy(viewState = ViewState.LOADING) }

            val characters = characterUseCase.getAllCharacters()
            if(characters.isNotEmpty()){
                _viewStateFlow.updateState {
                    copy(viewState = ViewState.SUCCESS, characters = characters)
                }
            }
        }

        fetchRemoteCharacter()
    }

    private fun fetchRemoteCharacter(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = characterUseCase.fetchRemoteCharacters()
                _viewStateFlow.updateState { copy(viewState = ViewState.SUCCESS, characters = characters) }
            }catch (e: Exception){
                _viewStateFlow.updateState{copy(viewState = ViewState.FAILED)}
            }
        }
    }

}

