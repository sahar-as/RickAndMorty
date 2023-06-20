package ir.saharapps.rickandmorty.ui.screen.characters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.saharapps.rickandmorty.R
import ir.saharapps.rickandmorty.databinding.ItemCharacterBinding
import ir.saharapps.rickandmorty.domain.model.Character
import ir.saharapps.rickandmorty.utils.*

class CharactersAdapter(
    private val onClick: (id: Int) -> Unit,
    private val onFavoriteClick: (id: Int, isFavorite: Boolean) -> Unit
): ListAdapter<Character, CharactersAdapter.CharactersViewHolder>(DiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            binding.imgCharacterPic.load(character.image)
            binding.txtCharacterName.text = character.name

            val selectedCharacter = getItem(adapterPosition)

            if(selectedCharacter.isFavorite){
                binding.txtAddFavorite.setText(R.string.remove_from_favorite)
                binding.txtAddFavorite.setBackgroundResource(R.drawable.round_dark_purple_background)
                binding.txtAddFavorite.setTextColor(Color.WHITE)
            }else{
                binding.txtAddFavorite.setText(R.string.add_to_favorite)
                binding.txtAddFavorite.setBackgroundResource(R.drawable.round_light_purple_background)
                binding.txtAddFavorite.setTextColor(Color.BLACK)
            }

            binding.imgCharacterPic.setOnClickListener {
                onClick(character.id)
            }

            binding.txtAddFavorite.setOnClickListener {txtAddFavorite ->
                onFavoriteClick(character.id, !character.isFavorite)
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