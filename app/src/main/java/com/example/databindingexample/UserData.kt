package com.example.databindingexample

import android.util.Log
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

public class UserData {
    public fun simpleData(){

    }
    companion object{
         suspend fun getTotalUsersData():Int{
            var count=0;
            lateinit var deffered: Deferred<Int>

            coroutineScope {
                launch(Dispatchers.IO) {
                    delay(2000)
                    count=100
                }
                deffered=async(IO) {
                    delay(4000)
                    return@async 70
                }
            }
            return count+deffered.await()

        }
    }

}