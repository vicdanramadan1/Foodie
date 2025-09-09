package org.betaapps.foodie.domain.model

import org.betaapps.foodie.domain.validation.InputValidationResult

data class Input (
    val text: String,
    val validationResult: InputValidationResult
)