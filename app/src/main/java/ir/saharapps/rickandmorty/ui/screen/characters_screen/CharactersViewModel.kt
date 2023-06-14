package ir.saharapps.rickandmorty.ui.screen.characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharactersUseCase
): ViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> = _charactersList

    private val viewStateChannel = Channel<ViewState>()
    val viewStateResult = viewStateChannel.receiveAsFlow()
    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            viewStateChannel.send(ViewState.Loading(true))
            try {
                _charactersList.value = characterUseCase.getAllCharacters()
                viewStateChannel.send(ViewState.Loading(false))
            }catch (e: Exception){
                viewStateChannel.send(ViewState.ConnectionError)
                viewStateChannel.send(ViewState.Loading(false))
            }

        }
    }

    sealed class ViewState(){
        class Loading(val loadState: Boolean): ViewState()
        object ConnectionError: ViewState()
    }



}