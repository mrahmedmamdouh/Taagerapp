package com.example.taagerapp.requests

import com.example.taagerapp.model.Product
import retrofit2.http.GET

interface ApiInterface {

    @GET("/taager/api/interview/products")
    suspend fun getProductsAsync() : List<Product>

}