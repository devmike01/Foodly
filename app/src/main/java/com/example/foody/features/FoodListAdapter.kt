package com.example.foody.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foody.R
import com.example.foody.repository.models.FoodResponse
import javax.inject.Inject

class FoodListAdapter @Inject constructor(): ListAdapter<FoodResponse, FoodListAdapter.WeatherAdapterVH>(diffUtil) {

    interface OnClickCityListener{
        fun onClickCity(foodResponse: FoodResponse)
    }

    private var onClickCityListener : OnClickCityListener? =null

    public fun setOnClickCityListener(onClickCityListener: OnClickCityListener){
        this.onClickCityListener = onClickCityListener
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<FoodResponse>(){
            override fun areItemsTheSame(
                oldItem: FoodResponse,
                newItem: FoodResponse
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FoodResponse,
                newItem: FoodResponse
            ): Boolean = oldItem == newItem

        }
    }

    inner class WeatherAdapterVH(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(foodResponse: FoodResponse){
            itemView.apply{
                val foodNameTv : TextView = findViewById(R.id.food_name_tv)
                val nutrientTv : TextView = findViewById(R.id.nutrient_tv)
                val quantityTv : TextView = findViewById(R.id.quantity_tv)
                val priceTv : TextView = findViewById(R.id.price_tv)
                val foodIv : ImageView= findViewById(R.id.food_iv)

                Glide.with(itemView.context).load(foodResponse.imageUrl).into(foodIv)

                setOnClickListener {
                    onClickCityListener?.onClickCity(foodResponse)
                }
                foodNameTv.text = foodResponse.productName
                nutrientTv.text = foodResponse.nutrients
                quantityTv.text = context.getString(R.string.title_quantity, foodResponse.quantity)
                priceTv.text = itemView.context.resources.getString(R.string.naira_amount, foodResponse.price)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
        return WeatherAdapterVH(view)
    }

    override fun onBindViewHolder(holder: WeatherAdapterVH, position: Int) {
        holder.bind(getItem(position))
    }
}