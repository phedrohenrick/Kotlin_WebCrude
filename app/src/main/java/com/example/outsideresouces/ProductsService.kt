package com.example.outsideresouces

import retrofit2.Response
import retrofit2.http.GET

interface ProductsService{

    @GET("/product")
    suspend fun getProducts(): Response<Products>
}