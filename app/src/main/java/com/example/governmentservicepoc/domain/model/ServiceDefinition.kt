package com.example.governmentservicepoc.domain.model

import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema

data class ServiceDefinition(

    val metadata: A2UiSchema,

    val workflowSteps: List<String>
)