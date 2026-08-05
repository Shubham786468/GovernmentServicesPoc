package com.example.governmentservicepoc.domain.usecase.passport

import com.example.governmentservicepoc.domain.agents.passport.PassportDocumentCheckAgent
import com.example.governmentservicepoc.domain.model.WorkflowContext

class VerifyDocumentsUseCase(
    private val eligibilityAgent: PassportDocumentCheckAgent
) {
    suspend operator fun invoke(
        aadhaar: String
    ): Boolean {

        return eligibilityAgent.execute(
            WorkflowContext(
                mapOf(
                    "aadhaar" to aadhaar
                )
            )
        ).success
    }
}