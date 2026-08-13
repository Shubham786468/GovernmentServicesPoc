package com.example.governmentservicepoc.domain.agents.ui
//
//import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
//import com.example.governmentservicepoc.domain.agents.WorkflowPlannerAgent
//import com.example.governmentservicepoc.domain.model.ServiceDefinition
//
///**
// * introduced to act as a coordinator between A2UI and AGenUI.
// * orchestrator combines multiple service to use as a combination.
// */
//class UiAndWorkflowOrchestrator(
//    private val serviceSelectionAgent: ServiceSelectionAgent,
//    private val uiMetadataAgent: UiMetadataAgent,
//    private val workflowPlannerAgent: WorkflowPlannerAgent
//) {
//
//    suspend fun generateService(
//        prompt: String
//    ): ServiceDefinition {
//
//        /**
//         * as we are executing serivceSelection at start on Home Screen
//         * so not required here for now.
//         */
//        val serviceId = prompt
//
////        val serviceId =
////            serviceSelectionAgent
////                .resolveService(
////                    prompt
////                )
//
//        val metadata = uiMetadataAgent
//            .generateScreen(
//                serviceId
//            )
//
////        val workflowSteps = listOf(
////            "ELIGIBILITY",
////            "VERIFICATION",
////            "APPROVAL",
////            "SUBMIT"
////        )
//        val workflowSteps =
//            workflowPlannerAgent
//                .generateWorkflow(
//                    serviceId,
//                )
//
//        return ServiceDefinition(
//            metadata = metadata,
//            workflowSteps = workflowSteps
//        )
//    }
//
//
//}