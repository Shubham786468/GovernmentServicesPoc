package com.example.governmentservicepoc.domain.agents

import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext

//Hook for all agents to implement. Each agent will have its own implementation
// of the execute function, which will take in a WorkflowContext and return an AgentResult.
interface WorkflowAgent {

    suspend fun execute(
        context: WorkflowContext
    ): AgentResult

}