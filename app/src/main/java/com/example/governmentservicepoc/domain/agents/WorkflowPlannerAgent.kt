package com.example.governmentservicepoc.domain.agents

import android.util.Log
import com.example.governmentservicepoc.adk.AdkRunnerManager
import com.google.adk.kt.events.Event
import com.google.adk.kt.types.Content
import com.google.adk.kt.types.Part
import com.google.gson.Gson
import java.util.UUID
import javax.inject.Inject

/**
 * WorkflowPlannerAgent is responsible for generating workflow steps for a given service ID.
 * It interacts with the ADK runner to process the prompt and extract the workflow steps.
 */
class WorkflowPlannerAgent @Inject constructor(
    private val runnerManager: AdkRunnerManager
) {

    suspend fun generateWorkflow(
        serviceId: String
    ): List<String> {

        val runner =
            runnerManager.getWorkflowRunner()

        val userId = "gov-poc"

        val sessionId =
            UUID.randomUUID()
                .toString()

        val prompt = """
            Generate workflow steps for:
            $serviceId

            Return only comma separated steps.
        """

        val events = runner.run(
            userId = userId,
            sessionId = sessionId,
            newMessage = Content(
                role = "user",
                parts = listOf(
                    Part(text = prompt)
                )
            ),
        ).asSequence().toList()

        val response = extractModelResponse(events)

//        Log.d("ADK_EVENTS_Workflow", Gson().toJson(events))

        if (response == "unknown") {
            Log.e("ServiceSelectionAgent", "Failed to resolve service from prompt: $prompt")
            throw IllegalArgumentException("Failed to resolve service from prompt: $prompt")
        } else {
            Log.d("ServiceSelectionAgent", "Resolved service: $serviceId from prompt: $prompt")
        }

        return response.toString()
            .split(",")
            .map { it.trim() }
    }

    /**
     * Extracts the model's response from the list of events.
     * It looks for the first event that has non-empty content parts and returns the text of the first part.
     * If no such event is found, it returns "unknown".
     *
     * @param events The list of events to extract the response from.
     * @return The extracted response text or "unknown" if not found.
     */
    private fun extractModelResponse(
        events: List<Event>
    ): String {

        return events
            .asReversed()
            .firstOrNull {
                !it.content?.parts.isNullOrEmpty() &&
                        it.content?.parts?.firstOrNull()?.text != null
            }
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?.trim()
            ?: "unknown"
    }

}