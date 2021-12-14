package com.example.basicworktest.denise.mendez.di

import com.example.basicworktest.denise.domain.messagesexception.*
import com.example.basicworktest.denise.mendez.messageexception.MessageExceptionDetailResources
import com.example.basicworktest.denise.mendez.messageexception.NetworkMessageExceptionDetailResources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val messageExceptionModule = module {
    factory<MessageException> {
        MessageExceptionHandler(
            MessageExceptionDetailResources(androidContext().resources),
            ServerMessageException(MessageExceptionDetailResources(androidContext().resources)),
            NetworkMessageException(NetworkMessageExceptionDetailResources(androidContext().resources)),
            UnknownMessageException(MessageExceptionDetailResources(androidContext().resources)),
        )
    }
}