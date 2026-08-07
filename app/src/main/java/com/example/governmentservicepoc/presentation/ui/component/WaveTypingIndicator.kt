package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WaveTypingIndicator(
    modifier: Modifier = Modifier,
    bubbleColor: Color = Color(0xFFF1F1F1),
    dotColor: Color = MaterialTheme.colorScheme.primary
) {
    val transition = rememberInfiniteTransition(label = "wave")

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bubbleColor, shape = RoundedCornerShape(12.dp))
            .padding(18.dp, 18.dp, 18.dp, 14.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(3) { index ->

            val yOffset by transition.animateFloat(
                initialValue = 0f,
                targetValue = -8f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200

                        0f at 0
                        0f at index * 250
                        -8f at (index * 250) + 250
                        0f at (index * 250) + 500
                    }
                ),
                label = "dot-$index"
            )

            Box(
                modifier = Modifier
                    .offset(y = yOffset.dp)
                    .size(8.dp)
                    .background(dotColor, CircleShape)
            )
        }
    }
}