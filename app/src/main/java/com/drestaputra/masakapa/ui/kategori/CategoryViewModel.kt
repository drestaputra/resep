package com.drestaputra.masakapa.ui.kategori

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.drestaputra.masakapa.api.RetrofitClient
import com.drestaputra.masakapa.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CategoryViewModel: ViewModel(){
    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> get() = _categories
    val _tempCategories  = MutableLiveData<List<Category>>()


    private var viewModelJob = Job()
    private  val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

//    untuk pencarian, data string dari pencarian dinamis (jadi mutable)
    private val _searchStringLiveData = MutableLiveData<String>()


    override fun onCleared() {
        super.onCleared()
//        ketika class sudah tidak digunakan coroutine akan berhenti sehingga tidak crash jika ada task pending
        viewModelJob.cancel()
    }
    init {
        _searchStringLiveData.value=""
        getCategories()
    }
    private fun  getCategories(){

        coroutineScope.launch {
            val getCategoriesDeferred = RetrofitClient.getRetrofitInstance().getCategories()
            try {
                val listResult = getCategoriesDeferred.await()
                _categories.postValue( listResult.categories)
                _tempCategories.postValue(listResult.categories)
            }catch (e: Exception){
                _categories.postValue(ArrayList())
                _tempCategories.postValue(ArrayList())
            }
        }
    }
    fun getCategoriesByName(string: String){
        var pencarian: List<Category> = ArrayList()
//        jika kosong set _tempCategories = _categories
        if (string.equals("") && string.equals("%%")){
            pencarian = _tempCategories.value!!
        }else{
            pencarian = ArrayList()
//            jika ada query pencarian, set _categories menjadi
            for (i in 0.._tempCategories.value!!.size-1){
                if (_tempCategories.value!![i].strCategory.lowercase(Locale.getDefault()).contains(string)) run {
                    pencarian.add(_tempCategories.value!![i])
                }
            }
        }
        _categories.postValue(pencarian.sortedBy { it.strCategory })
    }
    fun sortCategories(isAsc: Boolean){
        if (isAsc){
            _categories.postValue(_categories.value?.sortedBy { it.strCategory })
        }else{
            _categories.postValue(_categories.value?.sortedByDescending { it.strCategory })
        }
    }
}