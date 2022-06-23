package com.teckzi.rickandmorty.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.ItemEpisodeBinding
import com.teckzi.rickandmorty.presentation.screens.episodescreen.EpisodeFragmentDirections
import com.teckzi.rickandmorty.util.getEpisodeString

class EpisodeAdapter(context: Context) :
    PagingDataAdapter<EpisodeModel, EpisodeViewHolder>(DiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(layoutInflater.inflate(R.layout.item_episode, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val viewBinding by viewBinding(ItemEpisodeBinding::bind)

    fun bind(item: EpisodeModel?) {
        with(viewBinding) {
            itemEpisodeName.text = item?.name
            itemEpisodeNumber.text = item?.episode?.getEpisodeString()
            itemEpisodeAirDate.text = item?.airDate
            root.setOnClickListener {
                val id = item!!.id
                val action =
                    EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}