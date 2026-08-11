package com.example.governmentservicepoc.domain.agents.incomecertificate

import android.util.Log
import com.example.governmentservicepoc.adk.AdkRunnerManager
import com.google.adk.kt.events.Event
import com.google.adk.kt.types.Content
import com.google.adk.kt.types.Part
import com.google.gson.Gson
import kotlinx.coroutines.flow.toList
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceSelectionAgent @Inject constructor(
    private val runnerManager: AdkRunnerManager
) {

    suspend fun resolveService(
        prompt: String
    ): String {

        val runner =
            runnerManager.getServiceSelectionRunner()

        val userId = "gov-poc"

        val sessionId =
            UUID.randomUUID()
                .toString()

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

//        val events = runner.runAsync(
//            userId = userId,
//            sessionId = sessionId,
//            newMessage = Content(
//                role = "user",
//                parts = listOf(
//                    Part(text = prompt)
//                )
//            ),
//        ).toList()

        Log.d("ADK_EVENTS", Gson().toJson(events))


        val service = extractModelResponse(events)

        if (service == "unknown") {
            Log.e("ServiceSelectionAgent", "Failed to resolve service from prompt: $prompt")
            throw IllegalArgumentException("Failed to resolve service from prompt: $prompt")
        } else {
            Log.d("ServiceSelectionAgent", "Resolved service: $service from prompt: $prompt")
        }

        return service
    }

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


//    private fun parseServiceFromText(text: String): String {
//
//        return when {
//            text.contains(
//                "income_certificate",
//                true
//            ) ->
//                "income_certificate"
//
//            text.contains(
//                "passport",
//                true
//            ) ->
//                "passport"
//
//            text.contains(
//                "pension",
//                true
//            ) ->
//                "pension"
//
//            text.contains(
//                "driving_license",
//                true
//            ) ->
//                "driving_license"
//
//            else ->
//                "income_certificate"
//        }
//    }
}