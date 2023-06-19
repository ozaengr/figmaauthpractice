package com.desire.figmaauthpractice.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desire.figmaauthpractice.database.LoginDataClass
import com.desire.figmaauthpractice.database.LoginDatabase
import com.desire.figmaauthpractice.databinding.FragmentPasswordBinding
import com.desire.figmaauthpractice.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private val repo : UserRepository

    init {
        val userDao = LoginDatabase.getDatabase().loginDao()
        repo = UserRepository(userDao)
    }

    fun getUser(email : String, password : String): MutableLiveData<LoginDataClass>{
        val observer = MutableLiveData<LoginDataClass>()
        viewModelScope.launch(Dispatchers.IO) {
            observer.postValue(repo.getUser(email, password))
        }
        return observer
    }



}