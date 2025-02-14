package com.quiz.cartoons.domain

import android.content.Context
import com.quiz.cartoons.data.CartoonsResponse
import com.quiz.cartoons.data.DataState
import com.quiz.cartoons.network.repository.CartoonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCartoonsUseCase @Inject constructor(
    private val cartoonsRepository: CartoonsRepository
) {
    suspend operator fun invoke(): Flow<DataState<CartoonsResponse>>{
       return cartoonsRepository.getPersons().catch { e ->
           emit(DataState.Error("error en la peticion"))
       }.flowOn(Dispatchers.IO)
    }
}