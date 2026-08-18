package com.example.governmentservicepoc.presentation.ui.a2ui.model;

import kotlinx.serialization.Serializable

@Serializable
data class A2UiSchema(

    val screenId: String,

    val title: String,

    var screenBackgroundUrl: String?,

    val components: List<A2UiComponent>?,

    val appFunction: List<String>?,

    val actions: List<A2UiAction>?,

    val workflow: List<String>?,

    val layout: LayoutNode
)

