package com.example.basicworktest.denise.data.datasource.source

import com.example.basicworktest.denise.data.database.CharacterBreakingBad

interface LocalDataSource {
    suspend fun saveItem(item: CharacterBreakingBad)
    suspend fun deleteItemById(id: String)
    suspend fun getItems(): List<CharacterBreakingBad>
}