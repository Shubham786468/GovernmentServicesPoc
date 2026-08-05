package com.example.governmentservicepoc.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenMetadata(
    val screenId: String,
    val title: String,
//    val appFunction: List<String>,
    val actions: List<ActionMetadata>,
    var workflow: List<String>,
    val fields: List<Field>
)

@Serializable
data class Validation(
    val required: Boolean,
    val regex: String?,
    val errorMessage: String?
)

@Serializable
data class ActionMetadata(
    val id: String,
    val type: String,
    val label: String,
    val appFunction: String
)

@Serializable
data class Field(
    val id: String,
    val label: String,
    val type: String,
    val required: Boolean = false,
    @SerializedName("validation") val errorValidation: Validation?,
    val options: List<String>? = null
)