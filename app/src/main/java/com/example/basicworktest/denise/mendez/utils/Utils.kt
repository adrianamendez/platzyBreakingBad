package com.example.basicworktest.denise.mendez.utils

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}