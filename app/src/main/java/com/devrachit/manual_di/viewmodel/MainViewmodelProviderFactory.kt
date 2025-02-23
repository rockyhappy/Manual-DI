package com.devrachit.manual_di.viewmodel

import com.devrachit.manual_di.repository.UserRepository

interface Factory<T> {
    fun create(): T
}

/**Factory for [MainViewModel].
Since [MainViewModel] depends on [UserRepository], in order to create instances of
[MainViewModel], you need an instance of [UserRepository] that you pass as a parameter.
*/
class MainViewModelFactory(private val userRepository: UserRepository) : Factory<MainViewModel>  {
    override fun create(): MainViewModel {
        return MainViewModel(userRepository)
    }
}