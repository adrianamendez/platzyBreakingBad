package com.example.basicworktest.denise.domain.messagesexception

interface NetworkMessageExceptionResources : MessageExceptionResources {
    fun connectionTitle(): String
    fun connectionBody(): String
}