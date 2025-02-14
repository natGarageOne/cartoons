package com.quiz.cartoons.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiz.cartoons.data.CartoonsResponse
import com.quiz.cartoons.data.DataState
import com.quiz.cartoons.domain.GetCartoonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoonsViewModel @Inject constructor(
    private val getCartoonsUseCase: GetCartoonsUseCase
): ViewModel() {

    private val _cartoons = MutableStateFlow(CartoonsResponse())
    val cartoons: StateFlow<CartoonsResponse> get() = _cartoons

    fun getInitials(context: Context){
        viewModelScope.launch {
            getCartoonsUseCase.invoke(context).collect{ response ->
                when(response){
                    is DataState.Success -> {
                        _cartoons.value = response.data
                    }
                    is DataState.Error -> {
                        Log.e("Request", "error ${response.message}")

                    }
                    else ->{}
                }
            }
        }
    }
}