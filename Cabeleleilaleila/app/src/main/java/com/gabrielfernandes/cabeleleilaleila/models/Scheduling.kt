package com.gabrielfernandes.cabeleleilaleila.models

data class Scheduling(
    val id: Long? = null,
    val user: User,
    val date: String,
    val hour: String,
    val services: List<Service>
)
