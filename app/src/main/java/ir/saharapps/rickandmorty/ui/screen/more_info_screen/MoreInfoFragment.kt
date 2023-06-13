package ir.saharapps.rickandmorty.ui.screen.more_info_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentMoreInfoBinding

class MoreInfoFragment: Fragment(R.layout.fragment_more_info){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoreInfoBinding.bind(view)
    }
}