package com.example.governmentservicepoc.adk

import com.google.adk.kt.annotations.Tool

class WorkflowPlannerTool {

    @Tool
    fun getAvailableSteps(
        service: String
    ): List<String> {

        return when (service) {

            "income_certificate" -> {

                listOf(
                    "ELIGIBILITY",
                    "VERIFICATION",
                    "APPROVAL",
                    "SUBMIT"
                )
            }

            "passport" -> {

                listOf(
                    "DOCUMENT_CHECK",
                    "POLICE_VERIFICATION",
                    "PASSPORT_APPROVAL",
                    "SUBMIT"
                )
            }

            "pension" -> {

                listOf(
                    "SERVICE_VERIFICATION",
                    "RETIREMENT_CHECK",
                    "APPROVAL"
                )
            }

            else -> {

                listOf(
                    "ELIGIBILITY",
                    "VERIFICATION",
                    "APPROVAL",
                    "SUBMIT"
                )
            }
        }
    }
}