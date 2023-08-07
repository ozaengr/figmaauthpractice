package com.desire.figmaauthpractice.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desire.figmaauthpractice.database.User
import com.desire.figmaauthpractice.database.LoginDatabase
import com.desire.figmaauthpractice.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(){

    private val repo : UserRepository

    init {
        val userDao = LoginDatabase.getDatabase().loginDao()
        repo = UserRepository(userDao)
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

}