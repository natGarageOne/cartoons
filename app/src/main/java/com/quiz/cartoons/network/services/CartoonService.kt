package com.quiz.cartoons.network.services

import com.quiz.cartoons.data.CartoonsResponse
import retrofit2.http.GET

interface CartoonService {
    @GET("character")
    suspend fun getCharacters(): CartoonsResponse
}
