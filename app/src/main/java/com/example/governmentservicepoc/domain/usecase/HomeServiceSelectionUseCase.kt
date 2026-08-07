package com.example.governmentservicepoc.domain.usecase

import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
import javax.inject.Inject

class HomeServiceSelectionUseCase @Inject constructor(
    val serviceSelectionAgent: ServiceSelectionAgent
) {

    suspend operator fun invoke(prompt: String): String {
        return serviceSelectionAgent.resolveService(prompt)

    }
}