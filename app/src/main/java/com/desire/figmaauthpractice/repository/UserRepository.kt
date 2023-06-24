package com.desire.figmaauthpractice.repository

import androidx.lifecycle.LiveData
import com.desire.figmaauthpractice.database.LoginDao
import com.desire.figmaauthpractice.database.LoginDataClass
import kotlin.math.log

class   UserRepository (private val loginDao: LoginDao){

    var readAllData : LiveData<List<LoginDataClass>> = loginDao.readAllUsers()

    suspend fun addUser(loginDataClass: LoginDataClass){
        loginDao.addLoginData(loginDataClass)
    }

    suspend fun updateUser(loginDataClass: LoginDataClass){
        loginDao.updateUser(loginDataClass)
    }

    suspend fun deleUser(loginDataClass: LoginDataClass){
        loginDao.deleteUser(loginDataClass)
    }

    suspend fun deleteAll(){
        loginDao.deleteAll()
    }

    suspend fun getUser(email : String,password : String): LoginDataClass {
       return loginDao.getUser(email,password)

    }
}