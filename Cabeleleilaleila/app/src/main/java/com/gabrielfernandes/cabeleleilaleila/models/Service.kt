package com.gabrielfernandes.cabeleleilaleila.models

data class Service(
    val id: Long? = null,
    val name: String,
    val price: Float
){
    override fun toString(): String {
        return "${name}: R$ ${String.format("%.2f", price)}"
    }
}