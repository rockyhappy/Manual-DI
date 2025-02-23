package com.devrachit.manual_di.dataSource

import com.devrachit.manual_di.services.LoginService

class UserRemoteDataSource(
    private val loginService: LoginService
) {

}