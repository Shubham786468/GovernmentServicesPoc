package com.example.governmentservicepoc.domain.usecase

import com.example.governmentservicepoc.domain.agents.DynamicWorkflowEngine
import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository

class SubmitApplicationUseCase(

    private val repository: CitizenRepository,

    private val workflow: DynamicWorkflowEngine

) {

    suspend operator fun invoke(
        request: ApplicationRequest
    ): String {

        val context =
            WorkflowContext(
                formData = mapOf(
                    "fullName" to request.fullName,
                    "aadhaar" to request.aadhaar,
                    "annualIncome" to request.annualIncome.toString(),
                    "gender" to request.gender
                )
            )

        val workflowSteps = listOf(
            "ELIGIBILITY",
            "VERIFICATION",
            "APPROVAL"
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

        return repository.submitApplication(
            request
        )
    }
}