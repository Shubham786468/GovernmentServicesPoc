package com.example.governmentservicepoc.domain.usecase.passport

import com.example.governmentservicepoc.domain.agents.DynamicWorkflowEngine
import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import com.example.governmentservicepoc.utils.AppConstants

class SubmitPassportUseCase(
    private val repository: CitizenRepository,
    private val workflow: DynamicWorkflowEngine
) {

    suspend operator fun invoke(
        request: PassportApplicationRequest
    ): String {

        val context = WorkflowContext(
            formData = mapOf(
                AppConstants.FULL_NAME to request.fullName,
                AppConstants.DATE_OF_BIRTH to request.dateOfBirth,
                AppConstants.ADDRESS to request.address,
                AppConstants.NATIONALITY to request.nationality,
                AppConstants.AADHAAR_NUMBER to request.aadhaar,
                AppConstants.MOBILE_NUMBER to request.mobileNumber,
                AppConstants.EMAIL to request.email
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