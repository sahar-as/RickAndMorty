package ir.saharapps.rickandmorty.ui.screen.characters_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.saharapps.rickandmorty.databinding.ItemCharacterBinding
import ir.saharapps.rickandmorty.domain.model.Character

class CharactersAdapter(private val onClick: (id: Int) -> Unit): ListAdapter<Character, CharactersAdapter.CharactersViewHolder>(DiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            Glide.with(binding.root.context)
                .load(character.image)
                .into(binding.imgCharacterPic)
            binding.txtCharacterName.text = character.name

            binding.root.setOnClickListener {
                onClick(character.id)
            }
        }
    }

    class DiffCallBack: DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }


}