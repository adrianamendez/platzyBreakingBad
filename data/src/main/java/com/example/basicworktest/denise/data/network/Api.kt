package com.example.basicworktest.denise.data.network

import com.example.basicworktest.denise.data.network.model.CharacterDto
import com.example.basicworktest.denise.data.network.model.EpisodesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("characters")
    suspend fun charactersAsync(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<CharacterDto>

    @GET("episodes/{idEpisode}")
    suspend fun episodeAsync(
        @Path("idEpisode") idEpisode: Int
    ): List<EpisodesDto>
}