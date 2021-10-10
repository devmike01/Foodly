package com.example.foody.features

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.repository.FoodRepositoryImpl
import com.example.foody.repository.models.FoodResponse
import com.example.foody.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: FoodRepositoryImpl): ViewModel() {

    private val _foodLiveData = MutableLiveData<Resource<List<FoodResponse>>>()
    val foodLiveData : LiveData<Resource<List<FoodResponse>>> = _foodLiveData


    private val _foodDetailLiveData = MutableLiveData<FoodResponse>()
    val foodDetailLiveData : LiveData<FoodResponse> = _foodDetailLiveData

    fun getFood(){
        viewModelScope.launch(CoroutineExceptionHandler{_, exception ->
            _foodLiveData.value = Resource.failed(exception.message ?: "An unknown error has occurred!")

        } ){
            _foodLiveData.value = Resource.loading()
            val foodData = repository.getFood()

            _foodLiveData.value = Resource.success(foodData)
        }
    }

    fun showDetails(foodResponse: FoodResponse){
        _foodDetailLiveData.value = foodResponse
    }

}