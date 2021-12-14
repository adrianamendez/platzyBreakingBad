package com.example.basicworktest.denise.mendez.common

import com.example.basicworktest.denise.domain.messagesexception.MessageExceptionInfo

interface ErrorView {
    fun showError(messageExceptionInfo: MessageExceptionInfo)
}