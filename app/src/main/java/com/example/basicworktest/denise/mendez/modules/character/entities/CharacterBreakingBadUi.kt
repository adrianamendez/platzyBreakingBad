package com.example.basicworktest.denise.mendez.modules.character.entities

import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.utils.EMPTY_STRING
import com.example.basicworktest.denise.mendez.utils.*
import java.io.Serializable

data class CharacterBreakingBadUi(
    val charId: Int = 0,
    val name: String = EMPTY_STRING,
    val birthday: String = EMPTY_STRING,
    val occupation: String = EMPTY_STRING,
    val img: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val nickname: String = EMPTY_STRING,
    val appearance: String = EMPTY_STRING,
    val portrayed: String = EMPTY_STRING,
    val category: String = EMPTY_STRING,
    val isFavourite: Boolean = false
) : Serializable {
    companion object {
        fun mapFromDomain(item: CharacterBreakingBadEntity) = CharacterBreakingBadUi(
            item.charId,
            item.name,
            item.birthday,
            item.occupation.listStringToString(),
            item.img,
            item.status,
            item.nickname,
            item.appearance.listIntToString(),
            item.portrayed,
            item.category,
            item.isFavourite
        )
    }
}

fun CharacterBreakingBadUi.mapToDomain() = CharacterBreakingBadEntity(
    charId = this.charId,
    name = this.name,
    birthday = this.birthday,
    occupation = this.occupation.stringToListString(),
    img = this.img,
    status = this.status,
    nickname = this.nickname,
    appearance = this.appearance.stringToListInt(),
    portrayed = this.portrayed,
    category = this.category,
    isFavourite = true
)