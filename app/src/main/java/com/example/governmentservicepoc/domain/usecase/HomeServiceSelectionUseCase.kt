package com.example.governmentservicepoc.domain.usecase

import com.example.governmentservicepoc.data.repository.A2UiAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
import com.example.governmentservicepoc.domain.agents.ui.UiAgentOrchestrator
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema
import javax.inject.Inject

class HomeServiceSelectionUseCase @Inject constructor(
    val serviceSelectionAgent: UiAgentOrchestrator
) {

    suspend operator fun invoke(prompt: String): A2UiSchema {
        return serviceSelectionAgent.generateUi(prompt)

    }
}