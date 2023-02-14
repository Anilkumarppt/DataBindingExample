package com.example.databindingexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databindingexample.room.model.Subscriber

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase:RoomDatabase() {

    abstract val subscriberDao:SubscriberDAO

    companion object{
        @Volatile
        private var INSTANCE:SubscriberDatabase?=null
        fun getDataBaseInstance(context: Context):SubscriberDatabase{
            synchronized(this){
                var instance:SubscriberDatabase?= INSTANCE
                if(instance== null)
                    instance= Room.databaseBuilder(context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_database").
                    build()
                INSTANCE=instance
                return instance
            }
        }
    }
}