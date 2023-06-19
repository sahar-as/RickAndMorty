package ir.saharapps.rickandmorty.ui.screen.detailcharacter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentDetailCharacterBinding
import ir.saharapps.rickandmorty.domain.model.DetailCharacter
import ir.saharapps.rickandmorty.domain.model.Episode
import ir.saharapps.rickandmorty.domain.model.ViewState
import ir.saharapps.rickandmorty.utils.load
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterFragment: Fragment(R.layout.fragment_detail_character){

    private lateinit var binding: FragmentDetailCharacterBinding

    private val detailCharacterAdapter = DetailCharacterAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailCharacterBinding.bind(view)

        val detailCharacterViewModel: DetailCharacterViewModel by viewModels()



        val args: DetailCharacterFragmentArgs by navArgs()

        detailCharacterViewModel.getCharacterDetail(args.CharacterId)

        binding.rvEpisodes.apply {
            layoutManager = GridLayoutManager(this.context, 5)
            adapter = detailCharacterAdapter
        }


        lifecycleScope.launch {
            detailCharacterViewModel.viewStateFlow.collect{ viewState ->
                when(viewState.viewState){
                    ViewState.INITIAL -> {binding.pbLoading.visibility = View.INVISIBLE}
                    ViewState.LOADING -> {binding.pbLoading.visibility = View.VISIBLE}
                    ViewState.SUCCESS -> {
                        onSuccessState( viewState.detailCharacter, viewState.episodeList)
                    }
                    ViewState.FAILED -> {
                        Toast.makeText(requireContext(), getString(R.string.went_wrong_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun onSuccessState(detailCharacter: DetailCharacter?, episode: List<Episode>){
        binding.pbLoading.visibility = View.INVISIBLE
        detailCharacter?.let { updateUiValue(detailCharacter) }
        detailCharacterAdapter.submitList(episode)
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

