package com.quiz.cartoons.network.services

interface ApiService {
    fun <T> create(service: Class<T>): T
}