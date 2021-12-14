package com.example.basicworktest.denise.domain.exceptions

import com.example.basicworktest.denise.domain.utils.EMPTY_STRING

class NotificationNotFoundException(message: String = EMPTY_STRING) : RuntimeException(message)
