package com.teckzi.rickandmorty.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.teckzi.domain.model.CharacterModel

class DiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return when (oldItem) {
            is CharacterModel -> {
                (oldItem as CharacterModel).id == (newItem as CharacterModel).id
            }

            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return when (oldItem) {
            is CharacterModel -> {
                oldItem as CharacterModel == newItem as CharacterModel
            }
            else -> false
        }
    }
}