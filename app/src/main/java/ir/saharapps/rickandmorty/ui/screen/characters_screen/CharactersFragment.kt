package ir.saharapps.rickandmorty.ui.screen.characters_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentCharactersBinding

@AndroidEntryPoint
class CharactersFragment: Fragment(R.layout.fragment_characters) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharactersBinding.bind(view)

        val charactersViewModel: CharactersViewModel by viewModels()
        val charactersAdapter = CharactersAdapter{
            //Todo get id and send it to other fragment
        }

        binding.rvCharacters.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = charactersAdapter
        }

        //todo update data for adapter
    }

}