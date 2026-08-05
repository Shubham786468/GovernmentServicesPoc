package com.example.governmentservicepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.governmentservicepoc.appfunctions.AppFunctionRegistry
import com.example.governmentservicepoc.domain.agents.ui.UiAndWorkflowOrchestrator
import com.example.governmentservicepoc.domain.model.Field
import com.example.governmentservicepoc.domain.model.FormState
import com.example.governmentservicepoc.presentation.state.DynamicUiState
import com.example.governmentservicepoc.presentation.state.SubmissionState
import com.example.governmentservicepoc.utils.HandleException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DynamicFormViewModel @Inject constructor(
    private val uiAndWorkflowOrchestrator: UiAndWorkflowOrchestrator,
    private val appFunctionRegistry: AppFunctionRegistry
) : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        _submissionState.value = SubmissionState.Error(
            "Submission Failed due to ${throwable.message ?: "Server issue."}"
        )
    }

    // Screen State
    private val _state = MutableStateFlow<DynamicUiState>(DynamicUiState.Loading)
    val state: StateFlow<DynamicUiState> = _state.asStateFlow()

    // Form State
    private val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> = _formState.asStateFlow()

    // Submission State
    private val _submissionState = MutableStateFlow<SubmissionState>(SubmissionState.Idle)
    val submissionState: StateFlow<SubmissionState> = _submissionState.asStateFlow()

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    /**
     * First load the JSON from ADK and A2UI & AGenUi.
     */
//    init {
//        loadScreen("Apply for Income Certificate")
//    }

    fun loadScreen(prompt: String) {

        viewModelScope.launch {
            try {
                val serviceDefinition = withContext(Dispatchers.IO) {
                    uiAndWorkflowOrchestrator.generateService(
                        prompt
                    )
                }
                serviceDefinition.metadata.workflow = serviceDefinition.workflowSteps

                _state.value = DynamicUiState.Success(serviceDefinition.metadata)
            } catch (e: Exception) {
                HandleException.handleError(exception = e) {
                    _state.value = DynamicUiState.Error(it)
                }
            }
        }
    }

//    fun updateValue(
//        key: String, value: String
//    ) {
//        val currentValues = _formState.value.values.toMutableMap()
//
//        currentValues[key] = value
//
//        _formState.value = FormState(values = currentValues)
//    }

    fun updateField(field: Field, value: String) {

        val error = validateField(field, value)

        _formState.update {
            it.copy(
                values = it.values + (field.id to value),
                errors = if (error == null) {
                    it.errors - field.id
                } else {
                    it.errors + (field.id to error)
                }
            )
        }
    }


    fun dismissDialog() {
        _submissionState.value = SubmissionState.Idle
    }

    /**
     * Execute the submit action in the workflow.
     *
     */
    fun executeAction(
        functionName: String
    ) {
        viewModelScope.launch(coroutineExceptionHandler) {

            validateForm()?.let { error ->
                _snackbarMessage.emit(error)
                return@launch
            }

            try {
                _submissionState.value = SubmissionState.Loading

                val payload = formState.value.values.mapValues { it.value as Any }

                //background App Function Registry execution task
                val applicationId = withContext(Dispatchers.IO) {
                    appFunctionRegistry.execute(
                        functionName, payload
                    )
                }

                _submissionState.value = SubmissionState.Success(applicationId.toString())

            } catch (e: Exception) {
                if (e is IOException)
                    HandleException.handleError(
                        exception = e,
                        isRejected = true
                    ) {
                        _submissionState.value = SubmissionState.Rejected(it)
                    }
                else _submissionState.value =
                    SubmissionState.Error(e.message ?: "Submission Failed")
            }
        }
    }


    private fun validateField(
        field: Field,
        value: String
    ): String? {

        val validation = field.errorValidation

        if (value.isBlank()) {
            return "${field.label} is required"
        }

        if (field.type == "date" && value.isNotBlank()) {
            try {
                val formatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                LocalDate.parse(value, formatter)
            } catch (e: Exception) {

                return validation?.errorMessage
                    ?: "Invalid Date"
            }
        }

        validation?.regex?.let { regex ->
            if (
                value.isNotBlank() &&
                !Regex(regex).matches(value)
            ) {
                return validation.errorMessage
                    ?: "Invalid ${field.label}"
            }
        }

        return null
    }


    private fun validateForm(): String? {

        val fields = (_state.value as DynamicUiState.Success).metadata.fields
        val formValues = _formState.value.values

        fields.forEach { field ->

            val value = formValues[field.id]?.trim().orEmpty()

            val error = validateField(field, value)

            if (error != null) {
                return error
            }
        }
        return null
    }

//    fun getFormValues(): Map<String, String> {
//        return _formState.value.values
//    }
//
//    fun submitApplication() {
//
//        viewModelScope.launch(coroutineExceptionHandler) {
//            try {
//                _submissionState.value = SubmissionState.Loading
//
//                delay(2000.milliseconds)
//
//                val values = _formState.value.values
//
//                val request = ApplicationRequest(
//
//                    fullName = values[AppConstants.FULL_NAME] ?: "",
//
//                    aadhaar = values[AppConstants.AADHAAR_NUMBER] ?: "",
//
//                    panCard = values[AppConstants.PAN_CARD],
//
//                    annualIncome = values[AppConstants.ANNUAL_INCOME]?.toDoubleOrNull() ?: 0.0,
//
//                    gender = values[AppConstants.GENDER] ?: ""
//                )
//
//                //background task
//                val applicationId = withContext(Dispatchers.IO) {
//                    submitUseCase(request)
//                }
//
//                _submissionState.value = SubmissionState.Success(applicationId)
//
//            } catch (e: Exception) {
//
//                if (e is IOException)
//                    _submissionState.value = SubmissionState.Rejected(
//                        e.message ?: "Rejected"
//                    )
//                else _submissionState.value =
//                    SubmissionState.Error(e.message ?: "Submission Failed")
//            }
//        }
//    }
}