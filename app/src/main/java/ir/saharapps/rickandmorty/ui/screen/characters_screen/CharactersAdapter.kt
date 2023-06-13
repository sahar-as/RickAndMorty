package ir.saharapps.rickandmorty.ui.screen.characters_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.saharapps.rickandmorty.databinding.CharacterItemBinding
import ir.saharapps.rickandmorty.domain.model.RicKAndMorty

class CharactersAdapter: ListAdapter<RicKAndMorty, CharactersAdapter.RickMortyViewHolder>(DiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        val view = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RickMortyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RickMortyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class RickMortyViewHolder(private val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ricKAndMorty: RicKAndMorty){
            //assign image with glide
            binding.txtCharacterName.text = ricKAndMorty.name
        }
    }

    class DiffCallBack: DiffUtil.ItemCallback<RicKAndMorty>(){
        override fun areItemsTheSame(oldItem: RicKAndMorty, newItem: RicKAndMorty) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RicKAndMorty, newItem: RicKAndMorty) =
            oldItem == newItem
    }


}