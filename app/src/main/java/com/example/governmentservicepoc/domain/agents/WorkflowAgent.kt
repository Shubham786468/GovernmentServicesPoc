package com.example.governmentservicepoc.domain.agents

import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext

interface WorkflowAgent {

    suspend fun execute(
        context: WorkflowContext
    ): AgentResult

}