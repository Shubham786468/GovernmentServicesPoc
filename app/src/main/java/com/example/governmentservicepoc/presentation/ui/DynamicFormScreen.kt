package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.governmentservicepoc.domain.model.ActionMetadata
import com.example.governmentservicepoc.domain.model.Field
import com.example.governmentservicepoc.domain.model.ScreenMetadata
import com.example.governmentservicepoc.utils.AppConstants

@Composable
fun DynamicFormScreen(
    modifier: Modifier,
    metadata: ScreenMetadata,
    values: Map<String, String>,
    errors: Map<String, String>,
//    onValueChanged: (String, String) -> Unit,
    onValueChanged: (Field, String) -> Unit,
    onSubmit: (ActionMetadata) -> Unit
) {

    val scrollState = rememberScrollState()
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
    ) {

        Text(
            text = metadata.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )

        FormRenderer(
            fields = metadata.fields,
            values = values,
            errors = errors,
            onValueChanged = onValueChanged
        )

        metadata.actions.forEach { action ->
            if (action.type == AppConstants.PRIMARY_BUTTON_TYPE)
                Button(
                    onClick = { onSubmit(action) }
                ) {

                    Text(action.label)
                }
        }

        Spacer(
            modifier =
                Modifier.height(14.dp)
        )
    }
}