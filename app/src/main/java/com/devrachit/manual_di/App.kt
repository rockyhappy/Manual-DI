package com.devrachit.manual_di

import android.app.Application
import com.devrachit.manual_di.containers.AppContainer

class App:Application() {
    val appContainer = AppContainer()
}