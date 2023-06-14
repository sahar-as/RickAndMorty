package ir.saharapps.rickandmorty.ui.screen.more_info_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.saharapps.rickandmorty.databinding.ItemMoreInfoBinding

class MoreInfoAdapter: ListAdapter<List<String>, MoreInfoAdapter.MoreInfoViewHolder>(MoreInfoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreInfoViewHolder {
        val view = ItemMoreInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoreInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoreInfoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MoreInfoViewHolder(private val binding: ItemMoreInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(episode: List<String>){
            //todo bind episode info to the text
        }
    }

    class MoreInfoDiffCallBack: DiffUtil.ItemCallback<List<String>>(){
        override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>) =
            oldItem == newItem
    }
}