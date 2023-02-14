package com.example.databindingexample.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    /*We are going to use kotlin coroutines that why we use suspend keyword*/
    /*This method gives List of Albums*/
    @GET(value = "/albums")
    suspend fun getAlbums():Response<Album>
}