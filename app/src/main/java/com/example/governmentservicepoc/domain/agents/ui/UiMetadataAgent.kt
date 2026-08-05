package com.example.governmentservicepoc.domain.agents.ui

import com.example.governmentservicepoc.domain.model.ScreenMetadata
import com.example.governmentservicepoc.domain.repository.MetadataRepository

class UiMetadataAgent(
    private val metadataRepository: MetadataRepository
) {

    suspend fun generateScreen(
        serviceId: String
    ): ScreenMetadata {

        return metadataRepository.loadMetadata(
                "$serviceId.json"
            )
    }
}