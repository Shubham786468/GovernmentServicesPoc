package com.example.governmentservicepoc.domain.model

data class ApplicationRequest(

    val fullName: String,

    val aadhaar: String,

    val annualIncome: Double,

    val panCard: String? = null,

    val gender: String
)