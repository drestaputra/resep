package com.drestaputra.masakapa.ui.kategori

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drestaputra.masakapa.data.api.RetrofitClient
import com.drestaputra.masakapa.data.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoryViewModel: ViewModel(){
    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> get() = _categories

    private var viewModelJob = Job()
    private  val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()
//        ketika class sudah tidak digunakan coroutine akan berhenti sehingga tidak crash jika ada task pending
        viewModelJob.cancel()
    }
    init {
        getCategories()
    }
    private fun  getCategories(){
        coroutineScope.launch {
            val getCategoriesDeferred = RetrofitClient.getRetrofitInstance().getCategories()
            try {
                val listResult = getCategoriesDeferred.await()
                _categories.postValue( listResult.categories)
            }catch (e: Exception){
                _categories.postValue(ArrayList())
            }
        }
    }
}