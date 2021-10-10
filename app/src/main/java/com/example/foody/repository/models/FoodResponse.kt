package com.example.foody.repository.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodResponse {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("productName")
    @Expose
    var productName: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("nutrients")
    @Expose
    var nutrients: String? = null

    @SerializedName("quantity")
    @Expose
    var quantity: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("organic")
    @Expose
    var isOrganic = false

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (productName?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (from?.hashCode() ?: 0)
        result = 31 * result + (nutrients?.hashCode() ?: 0)
        result = 31 * result + (quantity?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + isOrganic.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        return result
    }
}