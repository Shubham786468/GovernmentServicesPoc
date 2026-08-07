package com.example.governmentservicepoc.utils

import android.content.Context
import com.example.governmentservicepoc.R

fun String.isValidName(): Boolean {
    return matches(Regex("^[A-Za-z ]+$"))
}

fun String.isValidAadhaar(): Boolean {
    return matches(Regex("^\\d{12}$"))
}

fun String.isValidIncome(): Boolean {
    return matches(Regex("^\\d+(\\.\\d{1,2})?$"))
}

fun String.isValidPan(): Boolean {
    return matches(Regex("^[A-Z]{6}[0-9]{4}[A-Z]$"))
}

fun String.isRequired(): Boolean {
    return isNotBlank()
}

fun String.getCertificateBackgroundImageUri(context: Context): String? = when {
    contains("income", ignoreCase = true) ->
        "android.resource://${context.packageName}/${R.drawable.income_certificate}"

    contains("passport", ignoreCase = true) ->
        "android.resource://${context.packageName}/${R.drawable.passport}"

    else -> null
}