package com.example.basicworktest.denise.mendez

import com.example.basicworktest.denise.data.network.model.CharacterDto
import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.mendez.modules.character.entities.CharacterBreakingBadUi

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

val mockedEmptyListBreakingBad = emptyList<CharacterBreakingBadEntity>()

val mockedBreakingBadUi = CharacterBreakingBadUi(
    1,
    "hank",
    "10-10-2010",
    "engineer",
    "url",
    "rip",
    "Hank",
    "1,2",
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
