package com.example.governmentservicepoc.data.remote

data class ApplicationIncomeRequestDto(

    val fullName: String,

    val aadhaar: String,

    val annualIncome: Double,

    val panCard: String? =null,

    val gender: String
)