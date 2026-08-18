package com.example.governmentservicepoc.data.repository

import com.example.governmentservicepoc.adk.AdkRunnerManager
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema
import com.google.adk.kt.events.Event
import com.google.adk.kt.types.Content
import com.google.adk.kt.types.Part
import com.google.gson.Gson
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class A2UiAgent @Inject constructor(
    private val runnerManager: AdkRunnerManager
) {

    suspend fun generateUi(
        service: String
    ): A2UiSchema {

        val runner = runnerManager.getServiceRunner()

        val response = runner.run(
            userId = "government-user",
            sessionId = UUID.randomUUID().toString(),
            newMessage = Content(
                role = "user",
                parts = listOf(
                    Part(text = service)
                )
            ),
            runConfig = null
        ).asSequence().toList()

        val json = extractModelResponse(response)

        return json
    }

    suspend fun generateServiceName(
        service: String
    ): String {

        val runner = runnerManager.getServiceSelectionRunner()

        val response = runner.run(
            userId = "government-user",
            sessionId = UUID.randomUUID().toString(),
            newMessage = Content(
                role = "user",
                parts = listOf(
                    Part(text = service)
                )
            ),
            runConfig = null
        ).asSequence().toList()

        val json = extractStringResponse(response)

        return json
    }

    private fun extractModelResponse(
        events: List<Event>
    ): A2UiSchema {
        val itemReceived = events
            .asReversed()
            .firstOrNull {
                !it.content?.parts.isNullOrEmpty() &&
                        it.content?.parts?.firstOrNull()?.text != null
            }
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?.removePrefix("```json")
            ?.removeSuffix("```")
            ?.trim()
            ?: "unknown"

        return Gson().fromJson(itemReceived, A2UiSchema::class.java)
    }

    private fun extractStringResponse(
        events: List<Event>
    ): String {
        val itemReceived = events
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

        return itemReceived.toString()
    }
}
