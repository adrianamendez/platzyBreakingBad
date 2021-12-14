package com.example.basicworktest.denise.mendez.common

import com.example.basicworktest.denise.mendez.utils.lifecycle.NavigationEvents
import com.example.basicworktest.denise.mendez.utils.asLiveData

abstract class BaseItemViewManager {

    protected val _navigationEvent = SingleLiveEvent<NavigationEvents>()
    val navigationEvent get() = _navigationEvent.asLiveData()
}