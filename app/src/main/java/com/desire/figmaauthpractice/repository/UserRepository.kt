package com.desire.figmaauthpractice.repository

import androidx.lifecycle.LiveData
import com.desire.figmaauthpractice.database.LoginDao
import com.desire.figmaauthpractice.database.User

class UserRepository(private val loginDao: LoginDao) {


    suspend fun readAllData(): List<User> {
        return loginDao.readAllUsers()
    }

    suspend fun addUser(user: User) {
        loginDao.addLoginData(user)
    }

    suspend fun updateUser(user: User) {
        loginDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        loginDao.deleteUser(user)
    }

    suspend fun deleteAll() {
        loginDao.deleteAll()
    }

    suspend fun getUser(email: String, password: String): User {
        return loginDao.getUser(email, password)

    }
}