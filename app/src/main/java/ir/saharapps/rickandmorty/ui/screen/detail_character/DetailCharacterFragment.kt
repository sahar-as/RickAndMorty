package ir.saharapps.rickandmorty.ui.screen.detail_character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentDetailCharacterBinding
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.ViewState
import ir.saharapps.rickandmorty.utils.load
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterFragment: Fragment(R.layout.fragment_detail_character){

    private lateinit var binding: FragmentDetailCharacterBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailCharacterBinding.bind(view)

        val detailCharacterViewModel: DetailCharacterViewModel by viewModels()
        val detailCharacterAdapter = DetailCharacterAdapter()
        val args: DetailCharacterFragmentArgs by navArgs()

        detailCharacterViewModel.getCharacterDetail(args.CharacterId)

        binding.rvEpisodes.apply {
            layoutManager = GridLayoutManager(this.context, 5)
            adapter = detailCharacterAdapter
        }


        lifecycleScope.launch {
            detailCharacterViewModel.viewState.collect{viewState ->
                when(viewState.viewState){
                    ViewState.INITIAL -> {binding.pbLoading.visibility = View.INVISIBLE}
                    ViewState.LOADING -> {binding.pbLoading.visibility = View.VISIBLE}
                    ViewState.SUCCESS -> {
                        binding.pbLoading.visibility = View.INVISIBLE
                        updateUiValue(viewState.detailCharacter)
                        detailCharacterAdapter.submitList(viewState.episodeList)
                    }
                    ViewState.FAILED -> {
                        //todo load from database
                    }
                }
            }
        }
    }

    private fun updateUiValue(detailCharacter: DetailCharacter){
        binding.apply {
            imgCharacterPicture.load(detailCharacter.image)
            txtName.text = detailCharacter.name
            txtStatus.text = detailCharacter.status
            txtGender.text = detailCharacter.gender
            txtSpecies.text = detailCharacter.species
        }
    }
}

