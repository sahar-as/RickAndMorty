package ir.saharapps.rickandmorty.ui.screen.characters_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentCharactersBinding

class CharactersFragment: Fragment(R.layout.fragment_characters) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCharactersBinding.bind(view)


    }

}