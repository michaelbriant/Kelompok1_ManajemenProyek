package com.example.promanagement.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?
}