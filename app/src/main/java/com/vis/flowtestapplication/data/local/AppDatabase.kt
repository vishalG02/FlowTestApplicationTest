package com.vis.flowtestapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vis.flowtestapplication.data.local.dao.UserDao
import com.vis.flowtestapplication.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}