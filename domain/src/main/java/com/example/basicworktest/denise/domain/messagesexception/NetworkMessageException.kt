package com.example.basicworktest.denise.domain.messagesexception

import com.example.basicworktest.denise.domain.exceptions.NetworkException

open class NetworkMessageException(
    private val resources: NetworkMessageExceptionResources
) : GeneralMessageException(resources) {
    override fun process(error: Throwable, params: HashMap<String, Any>?): MessageExceptionInfo {
        if (error is NetworkException) {
            return MessageExceptionInfo(
                resources.connectionTitle(),
                resources.connectionBody(),
                error)
        }
        return super.process(error, params)
    }
}