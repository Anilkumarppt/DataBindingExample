package com.example.databindingexample.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.databindingexample.room.model.Subscriber
import java.nio.charset.CodingErrorAction.IGNORE
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query(value = "DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query(value="SELECT * FROM subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>

}