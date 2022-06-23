package com.teckzi.rickandmorty.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.domain.model.EpisodeModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.ItemEpisodeBinding
import com.teckzi.rickandmorty.presentation.screens.characterdetailscreen.CharacterDetailFragmentDirections
import com.teckzi.rickandmorty.util.getEpisodeString

class CharacterDetailsAdapter(context: Context, private val episodeList: List<EpisodeModel>) :
    RecyclerView.Adapter<CharacterDetailViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailViewHolder {
        return CharacterDetailViewHolder(
            layoutInflater.inflate(
                R.layout.item_episode,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterDetailViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }
}

class CharacterDetailViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding by viewBinding(ItemEpisodeBinding::bind)

    fun bind(item: EpisodeModel?) {
        with(viewBinding) {
            itemEpisodeName.text = item?.name
            itemEpisodeNumber.text = item?.episode?.getEpisodeString()
            itemEpisodeAirDate.text = item?.airDate
            root.setOnClickListener {
                val id = item!!.id
                val action =
                    CharacterDetailFragmentDirections.actionCharacterDetailFragmentToEpisodeDetailFragment(
                        episodeId = id
                    )
                Navigation.findNavController(it).navigate(action)
            }

        }
    }
}