package com.example.frth.api
import retrofit2.Call
import retrofit2.http.*
import com.example.frth.model.ResponseItem

//Interface to call GET request on specified endpoint.
interface APIInterface {
    @GET("hiring.json")
    fun getItemData(): Call<List<ResponseItem>>
}