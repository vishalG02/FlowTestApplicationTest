package com.vis.flowtestapplication.data.local

import kotlinx.coroutines.flow.Flow
import com.vis.flowtestapplication.data.local.entity.User

interface DatabaseHelper {

    fun getUsers(): Flow<List<User>>

    fun insertAll(users: List<User>): Flow<Unit>

}