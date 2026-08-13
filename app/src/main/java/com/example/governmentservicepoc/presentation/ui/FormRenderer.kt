package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiComponent
import com.example.governmentservicepoc.presentation.ui.component.DynamicDateField
import com.example.governmentservicepoc.presentation.ui.component.DynamicDropdown
import com.example.governmentservicepoc.presentation.ui.component.DynamicNumberField
import com.example.governmentservicepoc.presentation.ui.component.DynamicTextField


@Composable
fun FormRenderer(
    fields: List<A2UiComponent>,
    values: Map<String, String>,
    errors: Map<String, String>,
    onValueChanged: (
        A2UiComponent,
        String
    ) -> Unit
) {
    val scrollState = rememberScrollState()

    Column {

        fields.forEach { field ->

            when (field.type) {

                "text" -> {

                    DynamicTextField(
                        field = field,
                        value =
                            values[field.id]
                                ?: "",
                        error = errors[field.id],
                        onValueChange = {
                            onValueChanged(
                                field,
                                it
                            )
                        }
                    )
                }

                "number" -> {

                    DynamicNumberField(
                        field = field,
                        value =
                            values[field.id]
                                ?: "",
                        error = errors[field.id],
                        onValueChange = {
                            onValueChanged(
                                field,
                                it
                            )
                        }
                    )
                }

                "date" -> {
                    DynamicDateField(
                        field = field,
                        value =
                            values[field.id]
                                ?: "",
                        error = errors[field.id],
                        onValueChange = {
                            onValueChanged(
                                field,
                                it
                            )
                        }
                    )
                }


                "dropdown" -> {

                    DynamicDropdown(
                        field = field,
                        value =
                            values[field.id]
                                ?: "",
                        error = errors[field.id],
                        onSelected = {
                            onValueChanged(
                                field,
                                it
                            )
                        }
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )
        }
    }
}
