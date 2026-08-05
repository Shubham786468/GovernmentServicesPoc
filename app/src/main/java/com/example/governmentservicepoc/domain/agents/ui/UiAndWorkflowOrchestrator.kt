package com.example.governmentservicepoc.domain.agents.ui

import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
import com.example.governmentservicepoc.domain.agents.WorkflowPlannerAgent
import com.example.governmentservicepoc.domain.model.ServiceDefinition

/**
 * introduced to act as a coordinator between A2UI and AGenUI.
 * orchestrator combines multiple service to use as a combination.
 */
class UiAndWorkflowOrchestrator(
    private val serviceSelectionAgent: ServiceSelectionAgent,
    private val uiMetadataAgent: UiMetadataAgent,
    private val workflowPlannerAgent: WorkflowPlannerAgent
) {

    suspend fun generateService(
        prompt: String
    ): ServiceDefinition {

        val serviceId =
            serviceSelectionAgent
                .resolveService(
                    prompt
                )

        val metadata = uiMetadataAgent
            .generateScreen(
                serviceId
            )

        val workflowSteps =
            workflowPlannerAgent
                .generateWorkflow(
                    serviceId,
                )

        return ServiceDefinition(
            metadata = metadata,
            workflowSteps = workflowSteps
        )
    }


}