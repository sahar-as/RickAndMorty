package ir.saharapps.rickandmorty.ui.screen.more_info_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentMoreInfoBinding
import ir.saharapps.rickandmorty.ui.screen.characters_screen.CharactersAdapter

@AndroidEntryPoint
class MoreInfoFragment: Fragment(R.layout.fragment_more_info){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoreInfoBinding.bind(view)

        val moreInfoViewModel: MoreInfoViewModel by viewModels()
        val moreInfoAdapter = MoreInfoAdapter()

        binding.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = moreInfoAdapter
        }

    }
}