package com.example.basicworktest.denise.domain.messagesexception

interface MessageException {
    fun process(error: Throwable, params: HashMap<String, Any>? = null): MessageExceptionInfo
}