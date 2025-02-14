package com.quiz.cartoons.network.repository

import com.quiz.cartoons.data.CartoonsResponse
import com.quiz.cartoons.data.DataState
import com.quiz.cartoons.network.services.CartoonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CartoonsRepositoryImpl @Inject constructor(
    private val service: CartoonService,
):CartoonsRepository{
    override suspend fun getPersons(): Flow<DataState<CartoonsResponse>> = flow {
        emit(DataState.Loading)
        val response = service.getCharacters()
        if (response.listPerson == null || response.listPerson?.size == 0) {
            emit(DataState.Error("Sin informacion"))
        } else emit(DataState.Success(response))
    }.catch { e ->
        emit(DataState.Error("Error en la peticion ${e.message}"))
    }.flowOn(Dispatchers.IO)

}