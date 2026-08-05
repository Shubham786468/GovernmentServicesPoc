package com.example.governmentservicepoc.appfunctions

interface DynamicFunctionExecutor {

    suspend fun execute(
        functionName:String,
        payload:Map<String,Any>
    ): Any
}