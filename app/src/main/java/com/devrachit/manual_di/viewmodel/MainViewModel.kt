package com.devrachit.manual_di.viewmodel

import androidx.lifecycle.ViewModel
import com.devrachit.manual_di.repository.UserRepository

class MainViewModel(
    private val userRepository: UserRepository
):ViewModel() {
    fun login(username: String, password: String) {
        // Do something with userRepository
    }
}