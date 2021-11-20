package com.drestaputra.masakapa.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//https://www.themealdb.com/api/json/v1/1/categories.php

class RetrofitClient {
    companion object {
        private const val BASE_URL: String = "https://www.themealdb.com/api/json/v1/1/"
        var retrofit: Retrofit? = null

        fun getRetrofitInstance(): APIList {
                OkHttpClient()
                    .newBuilder()
                    .readTimeout(200, TimeUnit.SECONDS)
                    .connectTimeout(200, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val request = original.newBuilder()
                            .addHeader("Content-Type","application/json")
                            .method(original.method(), original.body())
                            .build()
                        chain.proceed(request)
                    }
                    .build()

            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(APIList::class.java)


        }
    }


}