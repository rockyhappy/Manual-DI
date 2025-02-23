package com.devrachit.manual_di.containers

import com.devrachit.manual_di.model.UserData
import com.devrachit.manual_di.repository.UserRepository
import com.devrachit.manual_di.viewmodel.MainViewModelFactory

class UserContainer(val userRepository : UserRepository) {
    val userData = UserData()
    val mainViewModelFactory = MainViewModelFactory(userRepository)
}