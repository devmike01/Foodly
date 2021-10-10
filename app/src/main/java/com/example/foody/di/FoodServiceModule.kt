package com.example.foody.di

import android.content.Context
import com.example.foody.repository.FoodApi
import com.example.foody.repository.FoodService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object FoodServiceModule {

    @Provides
    fun provideFoodService(@ApplicationContext context: Context): FoodService {
        return FoodApi.Builder()
            .context(context)
            .gson(Gson())
            .build()
    }

}

