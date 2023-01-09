package com.hafidmust.core.utils

import com.hafidmust.core.data.local.entity.StoryEntity
import com.hafidmust.core.data.remote.response.ListStoryItem
import com.hafidmust.core.domain.model.Stories

object DataMapper {
    fun mapResponseToEntities(input: List<ListStoryItem>): List<StoryEntity> {
        val storiesList = ArrayList<StoryEntity>()
        input.map {
            val story = StoryEntity(
                id = it.id,
                photoUrl = it.photoUrl,
                createdAt = it.createdAt,
                name = it.name,
                description = it.description,
                lon = it.lon,
                lat = it.lat
            )
            storiesList.add(story)
        }
        return storiesList
    }

    fun mapEntitiesToDomain(input: List<StoryEntity>): List<Stories> =
        input.map {
            Stories(
                id = it.id,
                photoUrl = it.photoUrl,
                createdAt = it.createdAt,
                name = it.name,
                description = it.description,
                lon = it.lon,
                lat = it.lat,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Stories) = StoryEntity(
        id = input.id,
        photoUrl = input.photoUrl,
        createdAt = input.createdAt,
        name = input.name,
        description = input.description,
        lon = input.lon,
        lat = input.lat,
        isFavorite = input.isFavorite
    )
}