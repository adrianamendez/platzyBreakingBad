package com.example.basicworktest.denise.domain.messagesexception

import com.example.basicworktest.denise.domain.exceptions.UnknownException

open class UnknownMessageException(
    private val resources: MessageExceptionResources
) : GeneralMessageException(resources) {
    override fun process(error: Throwable, params: HashMap<String, Any>?): MessageExceptionInfo {
        if (error is UnknownException) {
            return MessageExceptionInfo(
                resources.messageTitle(),
                resources.messageBody(),
                error)
        }
        return super.process(error, params)
    }
}