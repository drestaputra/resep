package com.drestaputra.masakapa.data.api

import com.drestaputra.masakapa.data.model.KategoriPojo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface APIList {
//    untuk mengambil data kategori
//    https://www.themealdb.com/api/json/v1/1/categories.php
    @GET("categories.php")
    fun getCategories(): Deferred<KategoriPojo>
}