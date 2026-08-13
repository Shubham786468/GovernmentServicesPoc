package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiComponent

//@Composable
//fun DynamicNumberField(
//    field: Field,
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number
//        ),
//        label = {
//            Text(field.label)
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}

@Composable
fun DynamicNumberField(
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
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        label = {
            Text(field.label)
        },
        isError = !error.isNullOrBlank(),
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