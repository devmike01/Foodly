package com.example.foody.repository

import android.util.Log
import com.example.foody.repository.models.FoodResponse
import com.google.gson.Gson
import javax.inject.Inject

interface FoodRepository{
    fun getFood(): List<FoodResponse>
}

open class FoodRepositoryImpl @Inject constructor(private val foodService: FoodService): FoodRepository {

    override fun getFood(): List<FoodResponse> {
        return foodService.getFood()
    }

}