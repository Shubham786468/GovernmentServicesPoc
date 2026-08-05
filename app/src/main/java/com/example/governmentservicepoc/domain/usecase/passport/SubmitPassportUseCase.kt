package com.example.governmentservicepoc.domain.usecase.passport

import com.example.governmentservicepoc.domain.agents.DynamicWorkflowEngine
import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository

class SubmitPassportUseCase(
    private val repository: CitizenRepository,
    private val workflow: DynamicWorkflowEngine
) {

    suspend operator fun invoke(
        request: PassportApplicationRequest
    ): String {

        val context =
            WorkflowContext(
                formData = mapOf(
                    "fullName" to request.fullName,
                    "dateOfBirth" to request.dateOfBirth,
                    "address" to request.address,
                    "nationality" to request.nationality,
                    "aadhaar" to request.aadhaar,
                    "mobileNumber" to request.mobileNumber,
                    "email" to request.email
                )
            )

        val workflowSteps = listOf(
            "DOCUMENT_CHECK",
            "PASSPORT_SUBMISSION",
            "POLICE_VERIFICATION",
            "PASSPORT_APPROVAL",
        )

        val workflowResult =
            workflow.execute(
                workflowSteps,
                context
            )

        if (!workflowResult.success) {

            throw Exception(
                workflowResult.reason
                    ?: "Workflow Failed"
            )
        }

        return repository.submitPassportApplication(
            request
        )
    }
}