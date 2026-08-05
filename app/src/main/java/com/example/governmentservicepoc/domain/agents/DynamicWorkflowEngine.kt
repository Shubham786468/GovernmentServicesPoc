package com.example.governmentservicepoc.domain.agents

import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext

/**
 * Core component of AGenUI = Agent Generated Workflow Execution
 *
 * DynamicWorkflowEngine is responsible for executing a series of agents in a
 * defined workflow.
 * It retrieves agents from the AgentRegistry and executes them sequentially,
 * passing the WorkflowContext to each agent.
 * If any agent fails, the execution stops and returns the failure result.
 *
 * @property registry The AgentRegistry that holds all available agents.
 */

class DynamicWorkflowEngine(
    private val registry: AgentRegistry
) {

    suspend fun execute(
        workflow: List<String>,
        context: WorkflowContext
    ): AgentResult {

        workflow.forEach { step ->

            val result =
                registry.getAgent(step)
                    .execute(context)

            if (!result.success) {
                return result
            }
        }

        return AgentResult(true)
    }
}
