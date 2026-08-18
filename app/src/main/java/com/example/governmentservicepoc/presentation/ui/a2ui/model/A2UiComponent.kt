package com.example.governmentservicepoc.presentation.ui.a2ui.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class A2UiComponent(

    val id: String,

    val type: String,

    val label: String,

    val required: Boolean,

    val options: List<String>? = emptyList(),

    @SerializedName("validation") val errorValidation: ValidationSchema? = null
)


@Serializable
data class ValidationSchema(
    val required: Boolean,
    val regex: String?,
    val errorMessage: String?
)