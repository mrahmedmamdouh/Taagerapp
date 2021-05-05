package com.example.taagerapp.model.repository

import com.example.taagerapp.model.Product
import com.example.taagerapp.requests.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

     suspend fun getProducts(): List<Product>{
        return apiInterface.getProductsAsync()
    }
}