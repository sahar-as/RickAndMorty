package ir.saharapps.rickandmorty.ui.screen.detail_character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import ir.saharapps.rickandmorty.domain.usecase.CharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val id = savedStateHandle.get<Int>("CharacterId")

    private val _detailCharacter = MutableStateFlow<DetailCharacter?>(null)
    val detailCharacter: StateFlow<DetailCharacter?> = _detailCharacter

    private val _episodeList = MutableStateFlow<List<Episode>>(emptyList())
    val episodeList: StateFlow<List<Episode>> = _episodeList

    fun getCharacterDetail(){
        viewModelScope.launch {
            id?.let {
                _detailCharacter.value = useCase.getOneCharacter(id)

                val episodes = mutableListOf<Episode>()
                for(episode in _detailCharacter.value!!.episode){
                    val id = episode.split("episode/")[1]
                    episodes.add(Episode(id.toInt(), episode))
                }
                _episodeList.value = episodes
            }
        }
    }
}