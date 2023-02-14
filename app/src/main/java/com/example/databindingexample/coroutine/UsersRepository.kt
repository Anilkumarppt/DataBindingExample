package com.example.databindingexample.coroutine

import com.example.databindingexample.coroutine.model.UserModel
import kotlinx.coroutines.delay

class UsersRepository {
    suspend fun getUsers():List<UserModel>{

        delay(4000)

        return listOf(
            UserModel(1,"Hari"),
            UserModel(2,"Krishna"),
            UserModel(3,"Rama"),
            UserModel(4,"Sita")
        )
    }
}