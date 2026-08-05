package com.example.governmentservicepoc.utils

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