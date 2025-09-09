package org.betaapps.foodie.domain.validation



sealed class InputValidationResult() {

     object Loading : InputValidationResult()

    object Success : InputValidationResult()

   data class Error( val message: String) : InputValidationResult()
}
