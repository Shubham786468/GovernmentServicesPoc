package com.example.governmentservicepoc.domain.usecase

import com.example.governmentservicepoc.domain.agents.incomecertificate.EligibilityAgent
import com.example.governmentservicepoc.domain.model.WorkflowContext

class EligibilityUseCase(
    private val eligibilityAgent: EligibilityAgent
) {

    suspend operator fun invoke(
        income: Double
    ): Boolean {

        return eligibilityAgent.execute(
            WorkflowContext(
                mapOf(
                    "annualIncome" to income.toString()
                )
            )
        ).success
    }
}

