package com.example.governmentservicepoc.domain.model

import androidx.appfunctions.AppFunctionSerializable

@AppFunctionSerializable
data class StatusResult(

    val applicationId: String,

    val status: String
)