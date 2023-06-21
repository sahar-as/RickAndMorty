package ir.saharapps.rickandmorty.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.FavoriteViewState
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import ir.saharapps.rickandmorty.ui.screen.utility.updateState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: CharactersUseCase
): ViewModel() {

    private val _viewStateFlow = MutableStateFlow(FavoriteViewState())
    val viewStateFlow: StateFlow<FavoriteViewState> = _viewStateFlow.asStateFlow()

    fun getAllFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            val characters = useCase.getAllFavorite()
            if (characters.isNotEmpty()){
                _viewStateFlow.updateState { copy(characters = characters, emptyFavList = false) }
            }else{
            _viewStateFlow.updateState { copy(emptyFavList = true) }
            }
        }
    }

    fun updateFavoriteState(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val characters = useCase.updateFavStateReturnFavCharacter(id, false)
            if (characters.isNotEmpty()){
                _viewStateFlow.updateState { copy(characters = characters, emptyFavList = false) }
            }else{
                _viewStateFlow.updateState { copy(emptyFavList = true) }
            }
        }
    }
}