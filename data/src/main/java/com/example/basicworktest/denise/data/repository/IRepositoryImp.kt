package com.example.basicworktest.denise.data.repository

import com.example.basicworktest.denise.data.database.RoomDataSource
import com.example.basicworktest.denise.data.database.toDBItemEntity
import com.example.basicworktest.denise.data.database.toDomainItemEntity
import com.example.basicworktest.denise.data.datasource.source.RemoteDataSource
import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.entities.EpisodesBreakingBadEntity
import com.example.basicworktest.denise.domain.exceptions.ResultHandler
import com.example.basicworktest.denise.domain.exceptions.resultHandlerOf
import com.example.basicworktest.denise.usecases.repository.IRepository

class IRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localRepository: RoomDataSource
) : IRepository {

    private suspend fun setFavouriteFromDB(list: ArrayList<CharacterBreakingBadEntity>): List<CharacterBreakingBadEntity> {
        if (!list.isNullOrEmpty()) {
            val dataList = list.map { firstListElement ->
                getFavouriteItems()
                    .filter { it.charId == firstListElement.charId }
                    .lastOrNull()
                    .let { firstListElement.copy(isFavourite = it?.isFavourite ?: false) }
            }
            return dataList
        }
        return list
    }


    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): ResultHandler<List<CharacterBreakingBadEntity>> =
        resultHandlerOf {
            remoteDataSource.api.charactersAsync(
                offset,
                limit
            ).map { it.mapToDomain() }
        }

    override suspend fun getEpisodes(
        episodeId: Int
    ): ResultHandler<List<EpisodesBreakingBadEntity>> =
        resultHandlerOf {
            remoteDataSource.api.episodeAsync(
                episodeId
            ).map { it.mapToDomain() }
        }

    override suspend fun getFavouriteItems(): List<CharacterBreakingBadEntity> {
        val listEntity = mutableListOf<CharacterBreakingBadEntity>()
        localRepository.getItems().map {
            listEntity.add(it.toDomainItemEntity())
        }
        return listEntity.toList()
    }

    override suspend fun deleteFavouriteItems(id: String) {
        localRepository.deleteItemById(id)
    }

    override suspend fun saveFavouriteItems(item: CharacterBreakingBadEntity) {
        localRepository.saveItem(item.toDBItemEntity())
    }
}