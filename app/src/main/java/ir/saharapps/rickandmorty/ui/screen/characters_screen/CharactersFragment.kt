package ir.saharapps.rickandmorty.ui.screen.characters_screen

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
import kotlinx.coroutines.flow.collect
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

        lifecycleScope.launch {
            charactersViewModel.characterList.collect { characterList ->
                charactersAdapter.submitList(characterList)
            }
        }

        lifecycleScope.launch {
            charactersViewModel.viewStateResult.collect() { viewState ->
                when(viewState){
                    CharactersViewModel.ViewState.ConnectionError -> {
                        //Todo read from database
                    }
                    is CharactersViewModel.ViewState.Loading -> {
                        if(viewState.loadState){
                            binding.pbLoading.visibility = View.VISIBLE
                        }else{
                            binding.pbLoading.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }

    }

}