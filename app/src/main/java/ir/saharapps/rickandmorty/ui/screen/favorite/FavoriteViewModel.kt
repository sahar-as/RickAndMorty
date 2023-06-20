package ir.saharapps.rickandmorty.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
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

    private val _viewStateFlow = MutableStateFlow<List<Character>>(emptyList())
    val viewStateFlow: StateFlow<List<Character>> = _viewStateFlow

    fun getAllFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            val characters = useCase.getAllFavorite()
            if (characters.isNotEmpty()){
                _viewStateFlow.value = characters
            }
        }
    }
}