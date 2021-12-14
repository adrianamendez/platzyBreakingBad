package com.example.basicworktest.denise.domain.messagesexception

import com.example.basicworktest.denise.domain.exceptions.ServerException
import com.example.basicworktest.denise.domain.utils.value

open class ServerMessageException(
    private val resources: MessageExceptionResources
) : GeneralMessageException(resources) {
    override fun process(error: Throwable, params: HashMap<String, Any>?): MessageExceptionInfo {
        if (error is ServerException) {
            return MessageExceptionInfo(
                resources.messageTitle(),
                error.message.value(),
                error)
        }
        return super.process(error, params)
    }
}