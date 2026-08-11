package com.example.governmentservicepoc.adk

import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

/**
 * ServiceSelectionAdkAgent is an ADK agent that selects the most appropriate
 * government service based on the citizen's query. It uses a Gemini model for natural
 * language understanding and provides a set of tools to identify available government
 * services.
 */
object ServiceSelectionAdkAgent {

    val agent =
        LlmAgent(
            name = "government_service_selector",
            description = "Selects the most appropriate government service",
            model =
                Gemini(
                    name = AiConfig.AI_MODEL_NAME,
                    apiKey = AiConfig.AI_API_KEY
                ),
            instruction =
                Instruction(
                    """
                Identify the requested government service.
                
                Return ONLY one value:
                
                income_certificate
                passport
                pension
                driving_license
                none
                
                
                
                If not found any match return none.
                Do not return explanations.
                Do not return JSON.
                Do not return punctuation.
                Do not return markdown.
                Do not return additional text.
                """
                ),

            tools = GovernmentServiceTool().generatedTools()
        )
}