package ir.saharapps.rickandmorty.ui.screen.characters_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.saharapps.rickandmorty.databinding.CharacterItemBinding
import ir.saharapps.rickandmorty.domain.model.RicKAndMorty

class CharactersAdapter(private val onClick: (id: Int) -> Unit): ListAdapter<RicKAndMorty, CharactersAdapter.RickMortyViewHolder>(DiffCallBack()){

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
            //Todo assign image with glide
            binding.txtCharacterName.text = ricKAndMorty.name

            binding.root.setOnClickListener {
                onClick(ricKAndMorty.id)
            }
        }
    }

    class DiffCallBack: DiffUtil.ItemCallback<RicKAndMorty>(){
        override fun areItemsTheSame(oldItem: RicKAndMorty, newItem: RicKAndMorty) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RicKAndMorty, newItem: RicKAndMorty) =
            oldItem == newItem
    }


}