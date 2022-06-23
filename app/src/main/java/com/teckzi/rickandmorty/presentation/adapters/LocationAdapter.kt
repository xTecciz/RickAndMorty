package com.teckzi.rickandmorty.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.domain.model.LocationModel
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.ItemLocationBinding
import com.teckzi.rickandmorty.presentation.screens.locationscreen.LocationFragmentDirections

class LocationAdapter(context: Context) :
    PagingDataAdapter<LocationModel, LocationViewHolder>(DiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(layoutInflater.inflate(R.layout.item_location, parent, false))
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding by viewBinding(ItemLocationBinding::bind)

    fun bind(item: LocationModel?) {
        with(viewBinding) {
            itemLocationName.text = item?.name
            itemLocationInfo.text = "${item?.type} - ${item?.dimension}"
            root.setOnClickListener {
                val id = item!!.id
                val action =
                    LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}