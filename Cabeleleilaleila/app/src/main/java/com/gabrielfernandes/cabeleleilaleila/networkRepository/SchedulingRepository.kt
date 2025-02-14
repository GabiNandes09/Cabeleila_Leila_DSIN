package com.gabrielfernandes.cabeleleilaleila.networkRepository

import com.gabrielfernandes.cabeleleilaleila.models.Scheduling
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SchedulingRepository {

    @GET("scheduling")
    suspend fun getAll() : Response<List<Scheduling>>

    @GET("scheduling/{id}")
    suspend fun getById(
        @Path("id") id: Long
    ) : Response<Scheduling>

    @POST("scheduling")
    suspend fun insert(
        @Body scheduling: Scheduling
    ) : Response<Scheduling>
}