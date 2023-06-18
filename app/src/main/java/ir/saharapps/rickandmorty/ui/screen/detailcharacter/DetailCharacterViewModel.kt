package ir.saharapps.rickandmorty.ui.screen.detailcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.saharapps.rickandmorty.domain.model.DetailCharacterViewState
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
class DetailCharacterViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
): ViewModel() {

    private val _viewStateFlow = MutableStateFlow(DetailCharacterViewState())
    val viewStateFlow: StateFlow<DetailCharacterViewState> = _viewStateFlow.asStateFlow()

    fun getCharacterDetail(characterId: Int){

        viewModelScope.launch(Dispatchers.IO) {
            _viewStateFlow.updateState { copy(viewState = ViewState.LOADING) }
            try {
                val characterInfo = useCase.getCharacterById(characterId)
                val episode = useCase.getEpisodeList(characterId)
                _viewStateFlow.updateState {
                    copy(viewState = ViewState.SUCCESS, detailCharacter = characterInfo, episodeList = episode)
                }
            }catch (e: Exception){
                _viewStateFlow.updateState { copy(viewState = ViewState.FAILED) }
            }
        }

    }
}
