package com.vis.flowtestapplication.data.api

import kotlinx.coroutines.flow.Flow
import com.vis.flowtestapplication.data.model.ApiUser

interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>

}