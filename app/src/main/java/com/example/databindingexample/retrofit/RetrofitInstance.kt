package com.example.databindingexample.retrofit

import com.example.databindingexample.util.ConstantValues.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitInstance {
    /*When the class loads first then companion object block will execute */
    companion object{
        fun getRetrofitInstance(): Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build()
        }
    }
}