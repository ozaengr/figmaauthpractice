package com.desire.figmaauthpractice.retrofit

import retrofit2.Call
import com.desire.figmaauthpractice.home.RcvModel
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {


    //https://fakestoreapi.com/products
    @GET("products")
    fun getData(): Call<ArrayList<RcvModel>>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int):Call<Unit>

}