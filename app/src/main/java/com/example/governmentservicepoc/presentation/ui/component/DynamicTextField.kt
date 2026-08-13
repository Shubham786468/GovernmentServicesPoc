package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiComponent


@Composable
fun DynamicTextField(
    field: A2UiComponent,
    value: String,
    error: String? = null,
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.02f else 1f,
        label = "scale"
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(field.label) },
        interactionSource = interactionSource,
        isError = error != null,
        supportingText = {
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    )
}

//@Composable
//fun DynamicTextField(
//    field: Field,
//    value: String,
//    error: String? = null,
//    onValueChange: (String) -> Unit
//) {
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = {
//            Text(field.label)
//        },
//        isError = error != null,
//        supportingText = {
//            error?.let {
//                Text(
//                    text = it,
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}