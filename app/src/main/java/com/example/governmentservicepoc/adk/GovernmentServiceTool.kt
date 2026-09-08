package com.example.governmentservicepoc.adk

import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class GovernmentServiceTool {

    @Tool
    fun getServiceDefinition(
        @Param("Service name which is provided by screenId must always be lowercase like snake_case. Replace spaces with underscores ")
        service: String
    ): String {

        return when (service.lowercase()) {

            "income", "income_certificate" ->
                """
                Create form components:
                    - Full Name
                    - Aadhaar Number
                    - Annual Income
                    - Pan Card
                    - Gender
                    
                ActionButton: submitIncomeCertificate
                    
                Don't add any extra fields or properties that are not defined in the schema.

                Workflow:
                    ELIGIBILITY
                    VERIFICATION
                    APPROVAL
                    SUBMIT

                """.trimIndent()

            "passport_certificate", "passport", "passport_application" ->
                """
                Create form components:
                    - Full Name
                    - Date Of Birth
                    - Nationality
                    - Pan Card
                    - Email
                    - Gender
                    - Aadhaar Number
                    - Mobile Number
                    - Address
                    
               ActionButton: submitPassportApplication
                
               Don't add any extra fields or properties that are not defined in the schema.
               
                Workflow:
                    DOCUMENT_CHECK
                    POLICE_VERIFICATION
                    PASSPORT_APPROVAL
                    SUBMIT

                
                """.trimIndent()

            else ->
                "Unknown Service"
        }
    }
}