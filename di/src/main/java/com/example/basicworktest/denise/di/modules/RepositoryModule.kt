package com.example.basicworktest.denise.di.modules

import com.example.basicworktest.denise.data.repository.*
import com.example.basicworktest.denise.usecases.repository.IRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory<IRepository> {
        IRepositoryImp(get(), get())
    }
}
