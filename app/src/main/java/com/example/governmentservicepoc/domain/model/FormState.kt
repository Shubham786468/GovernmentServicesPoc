package com.example.governmentservicepoc.domain.model

data class FormState(
    val values: Map<String, String> = emptyMap(),
    val errors: Map<String, String> = emptyMap()
)