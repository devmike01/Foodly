package com.example.foody.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.io.InputStream

import com.example.foody.repository.models.FoodResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.IOException
import java.lang.NullPointerException
import java.nio.charset.Charset


class FoodApi(private val context: Context? =null, private val gson: Gson): FoodService{


    @Throws(IOException::class)
    private fun assetJSONFile(): String? {
        if (context == null){
            throw NullPointerException("context cannot be null!")
        }
        var json : String? = null
        try {
            val `is`: InputStream = context.assets.open("data.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.defaultCharset())

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    override fun getFood(): List<FoodResponse> {
        val myType = object : TypeToken<List<FoodResponse>>() {}.type
        return gson.fromJson(assetJSONFile(), myType)
    }

    class Builder{
        lateinit var gson : Gson
        private var context: Context? = null

        fun context(context: Context): Builder{
            this.context =context
            return this
        }

        fun gson(gson: Gson): Builder{
            this.gson = gson;
            return this
        }

        fun build(): FoodService {
            return FoodApi(context = context, gson = gson)
        }
    }

    //val foodService =

}

interface FoodService {
    fun getFood() : List<FoodResponse>
}