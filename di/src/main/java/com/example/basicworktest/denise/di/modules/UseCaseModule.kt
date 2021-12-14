package com.example.basicworktest.denise.di.modules

import com.example.basicworktest.denise.usecases.breakingbad.UseCase
import com.example.basicworktest.denise.usecases.breakingbad.UseCaseImp
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> {
        UseCaseImp(get())
    }
}