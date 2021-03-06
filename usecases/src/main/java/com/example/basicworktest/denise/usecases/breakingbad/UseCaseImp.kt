package com.example.basicworktest.denise.usecases.breakingbad

import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.entities.EpisodesBreakingBadEntity
import com.example.basicworktest.denise.domain.exceptions.ResultHandler
import com.example.basicworktest.denise.usecases.repository.IRepository

class UseCaseImp(
    private val iRepository: IRepository,
) : UseCase {

    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): ResultHandler<List<CharacterBreakingBadEntity>> =  iRepository.getCharacters(offset, limit)

    override suspend fun getEpisodes(
        episodeId: Int
    ): ResultHandler<List<EpisodesBreakingBadEntity>> =  iRepository.getEpisodes(episodeId)

    override suspend fun getFavouriteItems(): List<CharacterBreakingBadEntity> =
        iRepository.getFavouriteItems()

    override suspend fun deleteFavouriteItems(id: String) {
        iRepository.deleteFavouriteItems(id)
    }

    override suspend fun saveFavouriteItems(item: CharacterBreakingBadEntity) {
        iRepository.saveFavouriteItems(item)
    }
}