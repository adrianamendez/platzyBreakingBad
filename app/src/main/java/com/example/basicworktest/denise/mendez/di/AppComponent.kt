package com.example.basicworktest.denise.mendez.di

import com.example.basicworktest.denise.di.modules.dataSourceModule
import com.example.basicworktest.denise.di.modules.networkModule
import com.example.basicworktest.denise.di.modules.repositoryModule
import com.example.basicworktest.denise.di.modules.useCaseModule

object AppComponent {
    val appComponent = listOf(
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
        dataSourceModule,
        messageExceptionModule
    )
}