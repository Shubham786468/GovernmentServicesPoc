package com.example.governmentservicepoc.domain.model

import androidx.appfunctions.AppFunctionSerializable


@AppFunctionSerializable
data class PassportApplicationRequest(

    val fullName: String,

    val dateOfBirth: String,

    val gender: String,

    val address: String,

    val nationality: String,

    val aadhaar: String,

    val mobileNumber: String,

    val email: String
)
