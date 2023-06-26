package ir.saharapps.rickandmorty.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.commons.general.tracking.EventName
import ir.saharapps.commons.general.tracking.sendEvent
import ir.saharapps.rickandmorty.R
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
            try {
                characterUseCase.getAllCharacters(_viewStateFlow.value.pageNumber) {characters ->
                    _viewStateFlow.updateState {
                        copy(viewState = ViewState.SUCCESS, characters = characters, pageNumber = pageNumber)
                    }
                }
            }catch (e: Exception){
                _viewStateFlow.updateState{copy(viewState = ViewState.FAILED) }
            }
        }
    }

    fun updateFavoriteState(id: Int, isFavorite: Boolean){


        sendEvent(EventName.TRACKING_FAVORITE_CLICK)
        sendEvent(EventName.TRACKING_FAVORITE_CLICK, isFavorite.toString())

        viewModelScope.launch(Dispatchers.IO) {
            val characters = characterUseCase.updateFavStateReturnAllCharacter(id, isFavorite)
            if(characters.isNotEmpty()){
                _viewStateFlow.updateState {
                    copy(viewState = ViewState.SUCCESS, characters = characters, pageNumber = pageNumber)
                }
            }
        }
    }
}

