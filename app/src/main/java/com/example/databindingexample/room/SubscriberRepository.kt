package com.example.databindingexample.room

import com.example.databindingexample.room.model.Subscriber

class SubscriberRepository(private val dao: SubscriberDAO) {
    val subscribers=dao.getAllSubscribers();
    suspend fun insert(subscriber:Subscriber){
        dao.insertSubscriber(subscriber)
    }
    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }
    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }
    suspend fun deleteAllSubscribers(){
        dao.deleteAll()
    }
}