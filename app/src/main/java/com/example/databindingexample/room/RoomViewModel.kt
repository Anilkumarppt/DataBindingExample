package com.example.databindingexample.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databindingexample.room.model.Subscriber
import com.example.databindingexample.util.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RoomViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel() {

    val subscriber=subscriberRepository.subscribers
    private val message_=MutableLiveData<Event<String>>()
    val message:LiveData<Event<String>> = message_

    val inputName=MutableLiveData<String>()
    val inputEmail=MutableLiveData<String>()
    val saveButton=MutableLiveData<String>()
    val clearAll=MutableLiveData<String>()
    init {
        saveButton.value="Save"
        clearAll.value="Clear All"
    }
    fun saveOrUpdate(){
        val name=inputName.value!!
        val email=inputEmail.value!!
        insertRecords(Subscriber(0,name = name, email = email))
        inputEmail.value=""
        inputName.value=""
        message_.value= Event("Successfully Saved a new record!!")
    }
    fun clearAllRecords(){
        clearAll()
    }
    fun insertRecords(subscriber: Subscriber)=
        viewModelScope.launch(IO) {
            subscriberRepository.insert(subscriber)
    }
    fun updateRecords(subscriber: Subscriber)=viewModelScope.launch(IO) { subscriberRepository.update(subscriber) }
    fun deleteRecord(subscriber: Subscriber)=viewModelScope.launch(IO) { subscriberRepository.delete(subscriber) }
    fun clearAll()=viewModelScope.launch(IO) { subscriberRepository.deleteAllSubscribers() }
    // TODO: Implement the ViewModel
}