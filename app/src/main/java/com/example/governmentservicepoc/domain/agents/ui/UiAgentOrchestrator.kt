package com.example.governmentservicepoc.domain.agents.ui

import com.example.governmentservicepoc.data.repository.A2UiAgent
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiAgentOrchestrator
@Inject constructor(
    private val a2UiAgent: A2UiAgent
) {

    suspend fun generateUi(
        service: String
    ): A2UiSchema {

        return a2UiAgent
            .generateUi(service)
    }

    suspend fun generateServiceName(
        service: String
    ): String {

        return a2UiAgent
            .generateServiceName(service)
    }


}