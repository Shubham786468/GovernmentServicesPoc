package com.example.governmentservicepoc.domain.repository

import com.example.governmentservicepoc.domain.model.ScreenMetadata

interface MetadataRepository {

    suspend fun loadMetadata(
        fileName: String
    ): ScreenMetadata
}