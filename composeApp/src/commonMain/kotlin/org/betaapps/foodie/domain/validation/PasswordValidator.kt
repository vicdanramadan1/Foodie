package org.betaapps.foodie.domain.validation

object PasswordValidator : Validator<String> {
    override fun validate(value: String): InputValidationResult {
        return if (value.length < 6) {
            InputValidationResult.Error(message = "Password too short")
        } else {
            InputValidationResult.Success
        }
    }
}