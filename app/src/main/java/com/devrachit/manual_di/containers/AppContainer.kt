package com.devrachit.manual_di.containers

import com.devrachit.manual_di.dataSource.UserLocalDataSource
import com.devrachit.manual_di.dataSource.UserRemoteDataSource
import com.devrachit.manual_di.repository.UserRepository
import com.devrachit.manual_di.services.LoginService
import com.devrachit.manual_di.viewmodel.MainViewModelFactory
import retrofit2.Retrofit

/**Container of objects shared across the whole app*/
class AppContainer {

    /**
     * Since you want to expose [UserRepository] out of the container, you need to satisfy
     *its dependencies as you did before
     */

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)

    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    /**[UserRepository] is not private
     * it'll be exposed*/
    val userRepository = UserRepository(localDataSource, remoteDataSource)
    var userContainer : UserContainer?=null

}