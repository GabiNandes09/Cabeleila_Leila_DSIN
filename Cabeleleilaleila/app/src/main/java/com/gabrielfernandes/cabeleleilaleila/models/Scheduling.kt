package com.gabrielfernandes.cabeleleilaleila.models

import com.google.gson.annotations.SerializedName

data class Scheduling(
    val id: Long? = null,
    val user: User,
    val date: String,
    @SerializedName("hour")
    val hour: String,
    @SerializedName("services")
    val services: List<Service>
)
