package com.example.basicworktest.denise.usecases.repository

import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.entities.EpisodesBreakingBadEntity
import com.example.basicworktest.denise.domain.exceptions.ResultHandler

interface IRepository {

    suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): ResultHandler<List<CharacterBreakingBadEntity>>

    suspend fun getEpisodes(
        episodeId: Int
    ): ResultHandler<List<EpisodesBreakingBadEntity>>

    suspend fun getFavouriteItems(): List<CharacterBreakingBadEntity>

    suspend fun deleteFavouriteItems(id: String)

    suspend fun saveFavouriteItems(item: CharacterBreakingBadEntity)

}