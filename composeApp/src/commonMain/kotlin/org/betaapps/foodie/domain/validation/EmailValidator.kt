package org.betaapps.foodie.domain.validation

object EmailValidator : Validator<String> {
    val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"

    override fun validate(value: String): InputValidationResult {
        return if (value.isBlank()) {
            InputValidationResult.Error(message = "Email cannot be empty")
        } else if (Regex(regex).matches(value)) {
            InputValidationResult.Error( message = "Invalid email format")
        } else {
            InputValidationResult.Success
        }
    }
}