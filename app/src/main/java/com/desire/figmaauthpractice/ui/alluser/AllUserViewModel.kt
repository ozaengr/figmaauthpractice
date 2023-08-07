package com.desire.figmaauthpractice.ui.alluser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desire.figmaauthpractice.database.LoginDatabase
import com.desire.figmaauthpractice.database.User
import com.desire.figmaauthpractice.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllUserViewModel : ViewModel() {

    private val repo: UserRepository

    init {
        val userDao = LoginDatabase.getDatabase().loginDao()
        repo = UserRepository(userDao)
    }

    fun getUsers(): LiveData<List<User>> {
        val observer = MutableLiveData<List<User>>()
        viewModelScope.launch(Dispatchers.IO) {
            observer.postValue(repo.readAllData())
        }
        return observer
    }

    fun deleteUser(user: User) : MutableLiveData<Boolean> {
        val observer = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUser(user)
            observer.postValue(true)
        }
        return observer
    }

}