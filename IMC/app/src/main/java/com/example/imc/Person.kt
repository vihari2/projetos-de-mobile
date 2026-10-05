package com.example.imc

import java.io.Serializable

data class Person(
    val name: String,
    val weight: Double,
    val height: Double,
    val imc: Double,
    val message: String
) : Serializable
