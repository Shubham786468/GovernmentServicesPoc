package com.example.governmentservicepoc.data.remote

data class VerifyIncomeResponseDto(
    val id: String,
    val status: String,
    val verified: Boolean,
    val message: String
)

