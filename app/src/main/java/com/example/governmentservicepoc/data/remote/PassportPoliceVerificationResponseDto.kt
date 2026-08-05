package com.example.governmentservicepoc.data.remote

data class PassportPoliceVerificationResponseDto(
    val id: String,
    val status: String,
    val scheduled: Boolean,
    val message: String
)
