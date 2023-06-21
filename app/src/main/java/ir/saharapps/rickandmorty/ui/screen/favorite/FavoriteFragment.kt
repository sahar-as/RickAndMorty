package ir.saharapps.rickandmorty.ui.screen.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentFavoriteBinding
import ir.saharapps.rickandmorty.ui.screen.characters.CharactersAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var favoriteAdapter: CharactersAdapter

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteAdapter = CharactersAdapter(
            onClick = {characterId ->
                val action = FavoriteFragmentDirections.actionFragmentFavoriteToMoreInfoFragment(characterId)
                findNavController().navigate(action)
            },
            onFavoriteClick = {characterId, isFavorite ->
                favoriteViewModel.updateFavoriteState(characterId)
            }
        )
        favoriteViewModel.getAllFavorite()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        binding.txtNoFavorite.visibility = View.INVISIBLE

        binding.rvFavoriteCharacters.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = favoriteAdapter
            val dividerItemDecoration = DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            favoriteViewModel.viewStateFlow.collect{state ->
                when(state.emptyFavList){
                    true -> { noFaveUi(true) }
                    false -> {
                        noFaveUi(false)
                        favoriteAdapter.submitList(state.characters)
                    }
                }
            }
        }
    }

    private fun noFaveUi(isListEmpty: Boolean){
        if (isListEmpty){
            binding.txtNoFavorite.visibility = View.VISIBLE
            binding.rvFavoriteCharacters.visibility = View.INVISIBLE
        }else{
            binding.txtNoFavorite.visibility = View.INVISIBLE
            binding.rvFavoriteCharacters.visibility = View.VISIBLE
        }
    }
}