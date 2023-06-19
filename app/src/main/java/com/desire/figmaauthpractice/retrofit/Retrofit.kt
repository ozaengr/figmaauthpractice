package com.desire.figmaauthpractice.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val client =
        OkHttpClient().newBuilder().connectTimeout(20, TimeUnit.SECONDS).addInterceptor(interceptor)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    val apiInterface = retrofit.create(ApiInterface::class.java)


}
