package com.example.governmentservicepoc.domain.agents.incomecertificate

import com.example.governmentservicepoc.domain.agents.WorkflowAgent
import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import com.example.governmentservicepoc.utils.AppConstants

class VerificationAgent(
    private val repository: CitizenRepository
) : WorkflowAgent {

    override suspend fun execute(
        context: WorkflowContext
    ): AgentResult {

        val income =
            context.formData[AppConstants.ANNUAL_INCOME]?.toDoubleOrNull() ?: 0.0

        val verified =
            repository.verifyIncome(
                    income
                )

        return if (verified) {
            AgentResult(
                success = true
            )
        } else {
            AgentResult(
                success = false,
                reason = "Verification Failed"
            )
        }
    }
}
