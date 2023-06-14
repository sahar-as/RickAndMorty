package ir.saharapps.rickandmorty.ui.screen.detail_character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.saharapps.rickandmorty.databinding.ItemDetailCharacterBinding
import ir.saharapps.rickandmorty.domain.model.Episode

class DetailCharacterAdapter: ListAdapter<Episode, DetailCharacterAdapter.MoreInfoViewHolder>(MoreInfoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreInfoViewHolder {
        val view = ItemDetailCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoreInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoreInfoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MoreInfoViewHolder(private val binding: ItemDetailCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(episode: Episode){
            binding.txtEpisode.text = episode.id.toString()
        }
    }

    class MoreInfoDiffCallBack: DiffUtil.ItemCallback<Episode>(){
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem == newItem
    }
}