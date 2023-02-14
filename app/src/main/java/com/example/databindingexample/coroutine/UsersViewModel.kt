package com.example.databindingexample.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class UsersViewModel :ViewModel() {
    private var usersRepository:UsersRepository= UsersRepository()
     var usersList=liveData(Dispatchers.IO){
            val result=usersRepository.getUsers()
            emit(result)
    }
}