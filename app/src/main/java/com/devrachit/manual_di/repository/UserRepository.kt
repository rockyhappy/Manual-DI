package com.devrachit.manual_di.repository

import com.devrachit.manual_di.dataSource.UserLocalDataSource
import com.devrachit.manual_di.dataSource.UserRemoteDataSource

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {
}