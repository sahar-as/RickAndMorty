package ir.saharapps.rickandmorty.ui.screen.characters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandex.metrica.YandexMetrica
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.commons.general.tracking.EventName.TRACKING_FAVORITE_CLICK
import ir.saharapps.commons.general.tracking.sendEvent
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentCharactersBinding
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.domain.model.ViewState
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment: Fragment(R.layout.fragment_characters) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharactersBinding.bind(view)

        val charactersViewModel: CharactersViewModel by viewModels()

        val charactersAdapter = CharactersAdapter(
            onClick = {characterId ->
                val action = CharactersFragmentDirections.actionCharactersFragmentToMoreInfoFragment(characterId)
                findNavController().navigate(action)
                },
            onFavoriteClick = {characterId, isFavorite ->
                charactersViewModel.updateFavoriteState(characterId, isFavorite)
            }
        )

        charactersViewModel.getCharacters()

        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = charactersAdapter
            val dividerItemDecoration = DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch{
            charactersViewModel.viewStateFlow.collect{ state->
                when(state.viewState){
                    ViewState.INITIAL -> {binding.pbLoading.visibility = View.INVISIBLE}
                    ViewState.LOADING -> {binding.pbLoading.visibility = View.VISIBLE}
                    ViewState.SUCCESS -> {
                        binding.pbLoading.visibility = View.INVISIBLE
                        charactersAdapter.submitList(state.characters)
                    }
                    ViewState.FAILED -> {
                        handelFailedState(state.characters)
                    }
                }
            }
        }
    }
    private fun handelFailedState(characterList: List<Character>){
        if(characterList.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.went_wrong_message), Toast.LENGTH_SHORT).show()
        }
    }
}