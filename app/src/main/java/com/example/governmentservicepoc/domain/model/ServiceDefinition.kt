package com.example.governmentservicepoc.domain.model

data class ServiceDefinition(

    val metadata: ScreenMetadata,

    val workflowSteps: List<String>
)