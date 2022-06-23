package com.teckzi.rickandmorty.data.mappers

import com.teckzi.domain.model.CharacterModel
import com.teckzi.rickandmorty.data.local.model.CharacterDbo
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import com.teckzi.rickandmorty.util.addToIdList

fun CharacterDto.toCharacterModel() = CharacterModel(
    id = characterId,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.name,
    location = location.name,
    image = image,
    episode = episode.addToIdList()
)

fun CharacterDbo.toCharacterModel() = CharacterModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode
)

fun CharacterModel.toCharacterDbo() = CharacterDbo(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin,
    location = location,
    image = image,
    episode = episode
)
