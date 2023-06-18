package ir.saharapps.rickandmorty.ui.screen.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentCharactersBinding
import ir.saharapps.rickandmorty.domain.model.ViewState
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment: Fragment(R.layout.fragment_characters) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharactersBinding.bind(view)

        val charactersViewModel: CharactersViewModel by viewModels()

        val charactersAdapter = CharactersAdapter{characterId ->
            val action = CharactersFragmentDirections.actionCharactersFragmentToMoreInfoFragment(characterId)
            findNavController().navigate(action)
        }

        charactersViewModel.getCharacters()

        binding.rvCharacters.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = charactersAdapter
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
                        charactersViewModel.getLocalCharacters()
                    }
                }
            }
        }
    }
}