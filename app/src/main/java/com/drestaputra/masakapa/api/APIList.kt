package com.drestaputra.masakapa.api

import com.drestaputra.masakapa.model.KategoriPojo
import com.drestaputra.masakapa.model.MakananPojo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIList {
//    untuk mengambil data kategori
//    https://www.themealdb.com/api/json/v1/1/categories.php
    @GET("categories.php")
    fun getCategories(): Deferred<KategoriPojo>
//    untuk mengambil semua makanan dengan namaCategory X : String
//    https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
    @GET("filter.php")
    fun getMeals(@Query("c")  namaCategory: String): Deferred<MakananPojo>
}