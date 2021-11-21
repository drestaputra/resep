package com.drestaputra.masakapa.ui.pencarian

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drestaputra.masakapa.api.RetrofitClient
import com.drestaputra.masakapa.model.Category
import com.drestaputra.masakapa.model.Meal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class PencarianViewModel: ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals : LiveData<List<Meal>> get() = _meals
    private var viewModelJob = Job()
    private var namaString: String? =null
    private  val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init {
        getMealsData(namaString.toString())
    }
    fun getMealsData(namaStringP: String){
        coroutineScope.launch {
            val getMealsDeferred = RetrofitClient.getRetrofitInstance().getMealsByName(namaStringP)
            try {
                val listResult = getMealsDeferred.await()
                _meals.postValue(listResult.meals)
            }catch (e: Exception){
                _meals.postValue(ArrayList())
            }
        }
    }
    fun sortMeals(isAsc: Boolean){
        if (isAsc){
            _meals.postValue(_meals.value?.sortedBy { it.strMeal })
        }else{
            _meals.postValue(_meals.value?.sortedByDescending { it.strMeal })
        }
    }
}