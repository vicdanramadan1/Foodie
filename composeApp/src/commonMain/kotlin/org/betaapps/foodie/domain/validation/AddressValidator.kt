package org.betaapps.foodie.domain.validation

object AddressValidator : Validator<String> {
    override fun validate(value: String): InputValidationResult {
        return when {
            value.isBlank() ->
                InputValidationResult.Error("Address cannot be empty")

            !value.matches(Regex("^[A-Za-z0-9 .,#\\-]{5,100}\$")) ->
                InputValidationResult.Error("Invalid address format")

            else -> InputValidationResult.Success
        }
    }
}