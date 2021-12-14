package com.example.basicworktest.denise.domain.messagesexception.resources

interface NetworkMessageExceptionResources : MessageExceptionResources {
    fun connectionTitle(): String
    fun connectionBody(): String
}