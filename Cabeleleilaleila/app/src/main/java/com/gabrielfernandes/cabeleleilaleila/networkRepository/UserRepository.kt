package com.gabrielfernandes.cabeleleilaleila.networkRepository

import com.gabrielfernandes.cabeleleilaleila.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserRepository {

    @GET("users/{id}")
    suspend fun getById(
        @Path("id") id: Long
    ) : Response<User>

    @GET("users")
    suspend fun getAll() : Response<List<User>>

    @POST("users")
    suspend fun insert(
        @Body user: User
    ) : Response<User>
}