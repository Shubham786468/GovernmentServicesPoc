package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicDropdown(
    field: A2UiComponent,
    value: String,
    error: String? = null,
    onSelected: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrowRotation"
    )


    val borderColor by animateColorAsState(
        targetValue = if (expanded)
            MaterialTheme.colorScheme.primary
        else
            Color.Gray,
        label = "borderColor"
    )


    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.02f else 1f,
        label = "scale"
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(field.label)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation)
                )
            },
            isError = !error.isNullOrBlank(),
            supportingText = {
                if (!error.isNullOrBlank()) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            field.options?.forEach { item ->

                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}