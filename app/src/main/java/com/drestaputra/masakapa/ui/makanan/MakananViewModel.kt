package com.drestaputra.masakapa.ui.makanan

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

class MakananViewModel: ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals : LiveData<List<Meal>> get() = _meals
    private var viewModelJob = Job()
    private var categoryString: String? =null
    private  val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    val _tempMeal  = MutableLiveData<List<Meal>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init {
        if (categoryString !=null){
            getMealsData(categoryString!!)
        }
    }
    fun getMealsData(categoryP: String){
        coroutineScope.launch {
            val getMealsDeferred = RetrofitClient.getRetrofitInstance().getMeals(categoryP)
            try {
                val listResult = getMealsDeferred.await()
                _meals.postValue(listResult.meals)
                _tempMeal.postValue(listResult.meals)
            }catch (e: Exception){
                _meals.postValue(ArrayList())
                _tempMeal.postValue(ArrayList())
            }
        }
    }
    fun getMealsByName(string: String){
        var pencarian: List<Meal> = ArrayList()
//        jika kosong set _tempCategories = _categories
        if (string.equals("") && string.equals("%%")){
            pencarian = _tempMeal.value!!
        }else{
            pencarian = ArrayList()
//            jika ada query pencarian, set _categories menjadi
            for (i in 0.._tempMeal.value!!.size-1){
                if (_tempMeal.value!![i].strMeal.lowercase(Locale.getDefault()).contains(string)) run {
                    pencarian.add(_tempMeal.value!![i])
                }
            }
        }
        _meals.postValue(pencarian.sortedBy { it.strMeal })
    }
    fun sortMeals(isAsc: Boolean){
        if (isAsc){
            _meals.postValue(_meals.value?.sortedBy { it.strMeal })
        }else{
            _meals.postValue(_meals.value?.sortedByDescending { it.strMeal })
        }
    }
}