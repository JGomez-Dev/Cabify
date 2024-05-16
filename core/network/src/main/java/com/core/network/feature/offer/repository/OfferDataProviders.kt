package com.core.network.feature.offer.repository

import android.content.Context
import com.core.network.feature.offer.dto.PromotionDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class OfferDataProviders @Inject constructor(private val gson: Gson, private val context: Context){

    fun getOffers(): List<PromotionDTO> {
        val  jsonString= context.assets.open("promotions.json")
        val inputStream = jsonString.bufferedReader().use { it.readText() }
        return gson.fromJson(inputStream, object : TypeToken<List<PromotionDTO>>() {}.type)
    }


}