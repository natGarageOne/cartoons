package com.quiz.cartoons.network.repository

import com.quiz.cartoons.data.CartoonsResponse
import com.quiz.cartoons.data.DataState
import kotlinx.coroutines.flow.Flow

/**
 *@Author("Natanael chavez")
 *@Email("natanaelchavez07@gmail.com")
 */
interface CartoonsRepository {
    suspend fun getPersons(): Flow<DataState<CartoonsResponse>>
}