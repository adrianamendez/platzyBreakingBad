package com.example.basicworktest.denise.domain.messagesexception.resources

interface NotFoundMessageExceptionResources : MessageExceptionResources {
    fun notFoundTitle(): String
    fun notFoundBody(): String
}