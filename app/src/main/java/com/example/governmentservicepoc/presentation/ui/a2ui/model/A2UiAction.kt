package com.example.governmentservicepoc.presentation.ui.a2ui.model

import kotlinx.serialization.Serializable

@Serializable
data class A2UiAction(

    val id: String,

    val label: String,

    val type: String,

    val appFunction: String
)