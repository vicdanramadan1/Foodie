package org.betaapps.foodie.domain.validation


object UsernameValidator : Validator<String> {
    override fun validate(value: String): InputValidationResult {
        return when {
            value.isBlank() ->
                InputValidationResult.Error("Username cannot be empty")

            value.length < 3 ->
                InputValidationResult.Error("Username must be at least 3 characters")

            value.any { !it.isLetter() } ->
                InputValidationResult.Error("Username can only contain letters")

            else -> InputValidationResult.Success
        }
    }
}