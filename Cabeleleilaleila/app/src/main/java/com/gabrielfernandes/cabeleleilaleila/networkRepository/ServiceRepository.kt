package com.gabrielfernandes.cabeleleilaleila.networkRepository

import com.gabrielfernandes.cabeleleilaleila.models.Service
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceRepository {
    @GET("services")
    suspend fun getAll() : Response<List<Service>>

    @GET("services/{id}")
    suspend fun getById (
        @Path("id") id: Long
    ) : Response<Service>

    @POST("services")
    suspend fun insert(
        @Body service: Service
    ) : Response<Service>
}