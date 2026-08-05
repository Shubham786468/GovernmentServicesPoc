package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.governmentservicepoc.domain.model.ChatMessage

@Composable
fun ChatBubble(message: ChatMessage) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
        if (message.isAgent)
            Arrangement.Start
        else
            Arrangement.End
    ) {

        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.widthIn(max = 280.dp),
            color =
            if (message.isAgent)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.primary
        ) {

            Text(
                text = message.text,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.padding(10.dp),
                color =
                if (message.isAgent)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

