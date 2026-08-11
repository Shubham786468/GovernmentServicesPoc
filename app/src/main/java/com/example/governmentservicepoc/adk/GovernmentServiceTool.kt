package com.example.governmentservicepoc.adk

import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class GovernmentServiceTool {

    @Tool
    fun getAvailableServices(
        @Param("Citizen query")
        query: String
    ): List<String> {

        return listOf(
            "income_certificate",
            "passport",
            "pension",
            "driving_license",
            "none"
        )
    }
}