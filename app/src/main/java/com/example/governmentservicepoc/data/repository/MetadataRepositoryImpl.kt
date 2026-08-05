package com.example.governmentservicepoc.data.repository

import android.content.Context
import com.example.governmentservicepoc.domain.model.ScreenMetadata
import com.example.governmentservicepoc.domain.repository.MetadataRepository
import com.google.gson.Gson

/**
 * Implementation of [MetadataRepository] that reads metadata from a JSON file
 * in the assets folder.
 *
 * @property context The application context used to access the assets.
 */
class MetadataRepositoryImpl(
    private val context: Context
) : MetadataRepository {

    /**
     * Reading data from fake JSON.
     */
    override suspend fun loadMetadata(
        fileName: String
    ): ScreenMetadata {

        val json =
            context.assets
                .open(fileName)
                .bufferedReader()
                .use {
                    it.readText()
                }

        return Gson().fromJson(
            json,
            ScreenMetadata::class.java
        )
    }
}