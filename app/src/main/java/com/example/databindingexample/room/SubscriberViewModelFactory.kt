package com.example.databindingexample.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val repository: SubscriberRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomViewModel::class.java))
            return RoomViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}