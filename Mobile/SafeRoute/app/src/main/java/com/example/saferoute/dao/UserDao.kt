package com.example.saferoute.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.saferoute.domain.User

@Dao
interface UserDao {

    @Insert
    fun saveOne(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("SELECT * FROM user LIMIT 1")
    fun findRegistredUser(): User?

}