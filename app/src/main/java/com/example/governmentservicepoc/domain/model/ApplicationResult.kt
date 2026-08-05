package com.example.governmentservicepoc.domain.model

import androidx.appfunctions.AppFunctionSerializable

@AppFunctionSerializable
data class ApplicationResult(
    val applicationId: String,
    val status: String
)