package com.example.basicworktest.denise.mendez.messageexception

import android.content.res.Resources
import com.example.basicworktest.denise.domain.messagesexception.MessageExceptionResources
import com.example.basicworktest.denise.mendez.R


open class MessageExceptionDetailResources(
    private val resources: Resources
) : MessageExceptionResources {
    override fun messageTitle() = resources.getString(R.string.title_generic_exception_error)
    override fun messageBody() = resources.getString(R.string.body_generic_exception_error)
}