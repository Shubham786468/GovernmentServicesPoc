package com.example.governmentservicepoc.domain.agents.passport

import com.example.governmentservicepoc.domain.agents.WorkflowAgent
import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import com.example.governmentservicepoc.utils.AppConstants

class PassportDocumentCheckAgent(
    private val api: CitizenRepository
) : WorkflowAgent {
    override suspend fun execute(context: WorkflowContext): AgentResult {

        val documents = context.formData[AppConstants.AADHAAR_NUMBER]
            ?: throw IllegalArgumentException("Aadhaar number is required for document verification")

        val doc = api.verifyDocuments(documents)

        return if (doc) {
            AgentResult(success = true)
        } else {
            AgentResult(
                success = false,
                reason = "Missing required documents: Aadhaar card is mandatory for passport application."
            )
        }
    }

}