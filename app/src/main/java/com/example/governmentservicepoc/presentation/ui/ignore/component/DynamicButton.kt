package com.example.governmentservicepoc.presentation.ui.ignore.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun DynamicButton(
    label: String,
    onClick: () -> Unit
) {

    val containerColor = when (label.lowercase()) {
        "submit" -> Color(0xFF4F46E5)
        "save" -> Color(0xFF10B981)
        "approve" -> Color(0xFF22C55E)
        "reject" -> Color(0xFFEF4444)
        "cancel" -> Color(0xFF6B7280)
        else -> MaterialTheme.colorScheme.primary
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.05f else 1f,
        label = "scale"
    )

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
    ) {
        Text(
            text = label,
            Modifier.padding(vertical = 5.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}