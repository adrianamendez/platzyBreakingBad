package com.example.basicworktest.denise.domain.entities

data class EpisodesBreakingBadEntity(
    val episodeId: Int,
    val title: String,
    val season: String,
    val airDate: String,
    val characters: List<String>,
    val episode: Int,
    val series: String
)