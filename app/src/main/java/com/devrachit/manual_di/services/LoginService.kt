package com.devrachit.manual_di.services

import retrofit2.http.GET

interface LoginService {
    @GET("login")
    suspend fun login(username: String, password: String): Boolean
}