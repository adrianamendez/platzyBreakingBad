package com.example.basicworktest.denise.data.repository

import com.example.basicworktest.denise.data.network.model.CharacterDto
import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity

private const val CONTENT_TYPE = "application/json"

val mockedEntity = CharacterBreakingBadEntity(
    1,
    "hank",
    "10-10-2010",
    listOf("any", "any"),
    "url",
    "rip",
    "Hank",
    listOf<Int>(1, 2),
    "tks",
    "ww",
    false
)

val mockedDto = CharacterDto(
    1,
    "hank",
    "10-10-2010",
    listOf("any", "any"),
    "url",
    "rip",
    "Hank",
    listOf<Int>(1, 2),
    "tks",
    "ww"
)
