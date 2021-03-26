package com.florian_walther.offlinecache

import retrofit2.http.GET

interface RestaurantService {
    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<Restaurant>
}