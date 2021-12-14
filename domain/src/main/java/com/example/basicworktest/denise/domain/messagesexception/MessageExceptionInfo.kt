package com.example.basicworktest.denise.domain.messagesexception

import com.example.basicworktest.denise.domain.utils.EMPTY_STRING

data class MessageExceptionInfo(
    val title: String = EMPTY_STRING,
    val message: String = EMPTY_STRING,
    val error: Throwable,
    val params: HashMap<String, Any>? = null
)