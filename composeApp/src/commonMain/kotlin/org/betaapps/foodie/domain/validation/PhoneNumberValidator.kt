package org.betaapps.foodie.domain.validation


object PhoneNumberValidator : Validator<String> {
    override fun validate(value: String): InputValidationResult {
        return when {
            value.isBlank() ->
                InputValidationResult.Error("Phone number cannot be empty")

            !value.matches(Regex("^\\+?\\d{7,15}\$")) ->
                InputValidationResult.Error("Invalid phone number format")

            else -> InputValidationResult.Success
        }
    }
}