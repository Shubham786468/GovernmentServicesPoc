package com.example.governmentservicepoc.adk

import com.google.adk.kt.runners.InMemoryRunner
import com.google.adk.kt.sessions.InMemorySessionService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AdkRunnerManager is a singleton class that manages the runners for different ADK agents.
 * It provides access to the InMemoryRunner instances for
 * both ServiceSelectionAdkAgent and WorkflowPlannerAdkAgent.
 */
@Singleton
class AdkRunnerManager @Inject constructor() {

    private val sessionService = InMemorySessionService()
    private val adkServiceSelectionRunner by lazy {
        InMemoryRunner(
            appName = "GovernmentServicePOC",
            agent = ServiceSelectionAdkAgent.agent,
            sessionService = sessionService
        )
    }

//    private val adkWorkflowRunner by lazy {
//        InMemoryRunner(
//            appName = "GovernmentServicePOC",
//            agent = WorkflowPlannerAdkAgent.agent,
//            sessionService = sessionService
//        )
//    }


//    fun getWorkflowRunner(): InMemoryRunner {
//        return adkWorkflowRunner
//    }
    fun getServiceSelectionRunner(): InMemoryRunner {
        return adkServiceSelectionRunner
    }
}