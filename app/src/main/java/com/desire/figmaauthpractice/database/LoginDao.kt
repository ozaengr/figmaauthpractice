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
    suspend fun addLoginData(loginDataClass: LoginDataClass)

    @Query("SELECT * FROM login_table ORDER BY id ASC")
    fun readAllUsers(): LiveData<List<LoginDataClass>>

    @Insert
    suspend fun insertAll(vararg users: LoginDataClass)

    @Update
    suspend fun updateUser(loginDataClass: LoginDataClass)

    @Delete
    suspend fun deleteUser(loginDataClass: LoginDataClass)

    @Query("DELETE FROM login_table")
    fun deleteAll()

    @Query("SELECT * FROM login_table WHERE emailId LIKE:email AND password LIKE:password")
    suspend fun getUser(email : String, password : String) : LoginDataClass

}