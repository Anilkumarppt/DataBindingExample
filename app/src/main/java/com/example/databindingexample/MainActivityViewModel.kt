package com.example.databindingexample

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(stringInput:Int) : ViewModel() {

    private var total=MutableLiveData<Int>()
    public val totalCount:LiveData<Int> get() = total
    private  val TAG = "MainActivityViewModel"
    val inputText: MutableLiveData<String> =MutableLiveData<String>()

    init {
        total.value=stringInput

    }

    fun setCount(){
        val inputInt=inputText.value!!.toInt()
        total.value=(total.value)?.plus(inputInt)
    }
    fun downloadData(){
        for(i in 1..20000)
            Log.d(TAG, "downloadData: $i   Current Thread ${Thread.currentThread().name}")
    }
}