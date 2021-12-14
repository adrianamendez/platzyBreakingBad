package com.example.basicworktest.denise.mendez.messageexception

import android.content.res.Resources
import com.example.basicworktest.denise.domain.messagesexception.NetworkMessageExceptionResources
import com.example.basicworktest.denise.mendez.R

class NetworkMessageExceptionDetailResources(
    private val resources: Resources
) : MessageExceptionDetailResources(resources), NetworkMessageExceptionResources {
    override fun connectionTitle() = resources.getString(R.string.title_network_exception_error)
    override fun connectionBody() = resources.getString(R.string.body_network_exception_error)
}