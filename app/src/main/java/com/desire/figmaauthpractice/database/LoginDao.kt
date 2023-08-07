package com.desire.figmaauthpractice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLoginData(user: User)

    @Query("SELECT * FROM login_table ORDER BY id ASC")
    fun readAllUsers(): MutableList<User>

    @Insert
    suspend fun insertAll(vararg users: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM login_table")
    fun deleteAll()

    @Query("SELECT * FROM login_table WHERE emailId LIKE:email AND password LIKE:password")
    suspend fun getUser(email : String, password : String) : User


//   @Query("Update login_table SET name=:name WHERE emailId LIKE:email AND password LIKE:password")
//   suspend fun getData(name : String,email : String, password : String) : LoginDataClass
}