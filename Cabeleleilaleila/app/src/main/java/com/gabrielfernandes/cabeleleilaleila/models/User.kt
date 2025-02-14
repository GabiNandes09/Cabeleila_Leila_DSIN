package com.gabrielfernandes.cabeleleilaleila.models

data class User(
    val id: Long? = null,
    val username: String,
    val password: String,
    val email: String,
    val role: Role
)

enum class Role {
    ADMIN,
    USER
}