package com.example.basicworktest.denise.data.network.model

import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.entities.EpisodesBreakingBadEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodesDto(
    @field:Json(name = "episode_id")
    val episodeId: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "season")
    val season: String,
    @field:Json(name = "air_date")
    val airDate: String,
    @field:Json(name = "characters")
    val characters: List<String>,
    @field:Json(name = "episode")
    val episode: Int,
    @field:Json(name = "series")
    val series: String,
){
    fun mapToDomain() = EpisodesBreakingBadEntity(
        episodeId,
        title,
        season,
        airDate,
        characters,
        episode,
        series
    )
}