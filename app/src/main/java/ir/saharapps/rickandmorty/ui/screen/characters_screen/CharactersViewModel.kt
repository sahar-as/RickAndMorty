package ir.saharapps.rickandmorty.ui.screen.characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val remoteUseCase: CharactersUseCase
): ViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(emptyList())
    val characterList: StateFlow<List<Character>> = _charactersList

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            _charactersList.value = remoteUseCase.getAllCharacters()
        }
    }



}