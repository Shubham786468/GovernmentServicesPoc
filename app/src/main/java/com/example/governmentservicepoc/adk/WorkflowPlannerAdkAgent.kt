package com.example.governmentservicepoc.adk

import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

/**
 * WorkflowPlannerAdkAgent is an ADK agent that generates workflow steps for
 * a government service.
 * It uses the Gemini model to process instructions and generate the required workflow steps.
 */
object WorkflowPlannerAdkAgent {

    val agent = LlmAgent(

        name = "workflow_planner",

        description = "Generates workflow steps",

        model = Gemini(
            name = AiConfig.AI_MODEL_NAME,
            apiKey = AiConfig.AI_API_KEY
        ),

        instruction = Instruction(
            """
                Return workflow steps
                for a government service.
                """
        ),

        tools = WorkflowPlannerTool().generatedTools()
    )
}