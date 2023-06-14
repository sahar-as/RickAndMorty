package ir.saharapps.rickandmorty.ui.screen.detail_character

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentDetailCharacterBinding
import ir.saharapps.rickandmorty.utils.load
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterFragment: Fragment(R.layout.fragment_detail_character){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailCharacterBinding.bind(view)

        val detailCharacterViewModel: DetailCharacterViewModel by viewModels()
        val detailCharacterAdapter = DetailCharacterAdapter()

        detailCharacterViewModel.getCharacterDetail()

        binding.rvEpisodes.apply {
            layoutManager = GridLayoutManager(this.context, 5)
            adapter = detailCharacterAdapter
        }

        lifecycleScope.launch {
            detailCharacterViewModel.detailCharacter.collect(){detailCharacter ->
                detailCharacter?.let {
                    binding.apply {
                        imgCharacterPicture.load(detailCharacter.image)
                        txtName.text = detailCharacter.name
                        txtStatus.text = detailCharacter.status
                        txtGender.text = detailCharacter.gender
                        txtSpecies.text = detailCharacter.species
                    }
                }
            }
        }

        lifecycleScope.launch {
            detailCharacterViewModel.episodeList.collect{episodes ->
                detailCharacterAdapter.submitList(episodes)
            }
        }

    }
}