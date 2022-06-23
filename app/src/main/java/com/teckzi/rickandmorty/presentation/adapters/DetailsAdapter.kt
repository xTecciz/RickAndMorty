package com.teckzi.rickandmorty.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.teckzi.domain.model.CharacterModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.ItemCharacterBinding
import com.teckzi.rickandmorty.presentation.screens.episodedetailscreen.EpisodeDetailFragmentDirections
import com.teckzi.rickandmorty.presentation.screens.locationdetailscreen.LocationDetailFragmentDirections
import com.teckzi.rickandmorty.util.Constants.EPISODE_TYPE
import com.teckzi.rickandmorty.util.Constants.LOCATION_TYPE

class DetailsAdapter(
    context: Context,
    private val characterList: List<CharacterModel>,
    private val type: String
) :
    RecyclerView.Adapter<DetailViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(characterList[position], type)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}

class DetailViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding by viewBinding(ItemCharacterBinding::bind)

    fun bind(item: CharacterModel?, type: String) {
        with(binding) {
            itemCharacterImageView.load(item?.image) {
                placeholder(R.drawable.image_placeholder)
                transformations(CircleCropTransformation())
                error(R.drawable.image_placeholder)
            }
            itemCharacterNameTextView.text = item?.name
            itemCharacterInfo.text = "${item?.species}, ${item?.gender}"
            itemCharacterStatus.text = item?.status
            when (item?.status) {
                "Alive" -> itemCharacterStatus.setTextColor(Color.parseColor("#2EED0C"))
                "Dead" -> itemCharacterStatus.setTextColor(Color.parseColor("#FF0000"))
                "unknown" -> itemCharacterStatus.setTextColor(Color.parseColor("#C4FFFFFF"))
            }
            root.setOnClickListener {
                val id = item!!.id
                when (type) {
                    LOCATION_TYPE -> {
                        val action =
                            LocationDetailFragmentDirections.actionLocationDetailFragmentToCharacterDetailScreen(
                                id
                            )
                        Navigation.findNavController(it).navigate(action)
                    }
                    EPISODE_TYPE -> {
                        val action =
                            EpisodeDetailFragmentDirections.actionEpisodeDetailFragmentToCharacterDetailScreen(
                                id
                            )
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }
    }
}