package com.example.governmentservicepoc.domain.model

data class WorkflowResult(

    val approved: Boolean,

    val reason: String? = null
)