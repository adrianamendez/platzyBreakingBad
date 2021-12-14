package com.example.basicworktest.denise.mendez.di

import com.example.basicworktest.denise.mendez.modules.character.viewmodel.CharacterViewModel
import com.example.basicworktest.denise.mendez.utils.CoroutineContextProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharacterViewModel(get(), get(), get())
    }
    factory { CoroutineContextProvider() }
}
