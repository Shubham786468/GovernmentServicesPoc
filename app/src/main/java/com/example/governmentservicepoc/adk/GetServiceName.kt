package com.example.governmentservicepoc.adk

import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class GetServiceName {

    @Tool
    fun getAvailableSteps(
        @Param(description = "return name suitable for the task.")
        service: String
    ): List<String> {

        return listOf<String>(
            "income_certificate",
            "passport",
            "pension",
            "driving_license",
            "none"
        )

    }
}