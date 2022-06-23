package com.teckzi.rickandmorty.data.mappers

import com.teckzi.domain.model.LocationModel
import com.teckzi.rickandmorty.util.addToIdList
import com.teckzi.ricks.data.local.model.LocationDbo
import com.teckzi.ricks.data.network.model.LocationDto

fun LocationDto.toLocationModel() = LocationModel(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters.addToIdList()
)

fun LocationDbo.toLocationModel() = LocationModel(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters ?: emptyList()
)

fun LocationModel.toLocationDbo() = LocationDbo(
    id = id,
    name = name,
    dimension = dimension,
    type = type,
    characters = characters
)