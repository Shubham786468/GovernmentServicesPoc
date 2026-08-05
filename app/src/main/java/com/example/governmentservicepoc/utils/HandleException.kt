package com.example.governmentservicepoc.utils

import com.google.ai.client.generativeai.type.ServerException
import java.io.IOException

object HandleException {

    fun handleError(
        exception: Exception,
        isRejected: Boolean = false,
        onError: (String) -> Unit
    ) {
        val message = when (exception) {
            is IllegalArgumentException ->
                exception.message ?: "Invalid input"

            is ServerException ->
                exception.message ?: "Service temporarily unavailable. Please try again later."

            is IOException -> {
                if (isRejected) {
                    exception.message ?: "Rejected"
                } else {
                    exception.message ?: "Something went wrong."
                }
            }

            else ->
                exception.message ?: "Unknown error"
        }

        onError(message)
    }
}