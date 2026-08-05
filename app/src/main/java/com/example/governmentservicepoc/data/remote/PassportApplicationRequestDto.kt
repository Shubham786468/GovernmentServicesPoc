package com.example.governmentservicepoc.data.remote

import androidx.appfunctions.AppFunctionSerializable


data class PassportApplicationRequestDto(

    val fullName: String,

    val dateOfBirth: String,

    val gender: String,

    val address: String,

    val nationality: String,

    val aadhaarNumber: String,

    val mobileNumber: String,

    val email: String
)
