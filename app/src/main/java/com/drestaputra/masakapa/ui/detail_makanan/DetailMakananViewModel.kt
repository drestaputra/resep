package com.drestaputra.masakapa.ui.detail_makanan

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

class DetailMakananViewModel: ViewModel() {
    private val _meals = MutableLiveData<Meal>()
    val meals : LiveData<Meal> get() = _meals
    private var viewModelJob = Job()
    private var categoryString: String? =null
    private  val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init {
        if (categoryString !=null){
            getMealsData(categoryString!!)
        }
    }
    fun getMealsData(idMakanan: String){

        coroutineScope.launch {
            val getMealsDeferred = RetrofitClient.getRetrofitInstance().getMealDetail(idMakanan)
            try {
                val listResult = getMealsDeferred.await()
                if (listResult.meals.size != 0){
                    _meals.postValue(listResult.meals.get(0))
                }else{
                    _meals.postValue(null)
                }
            }catch (e: Exception){
                _meals.postValue(null)
            }
        }
    }
}