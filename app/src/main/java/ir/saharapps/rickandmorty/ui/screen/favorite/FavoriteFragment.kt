package ir.saharapps.rickandmorty.ui.screen.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.FragmentFavoriteBinding
import ir.saharapps.rickandmorty.ui.screen.characters.CharactersAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val favoriteViewModel: FavoriteViewModel by viewModels()

        val favoriteAdapter = CharactersAdapter(
            onClick = {},
            onFavoriteClick = {characterId, isFavorite ->
                //todo remove from favorite list
            }
        )

        favoriteViewModel.getAllFavorite()


        binding.rvFavoriteCharacters.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = favoriteAdapter
            val dividerItemDecoration = DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            favoriteViewModel.viewStateFlow.collect{characters ->
                favoriteAdapter.submitList(characters)
            }
        }


    }
}