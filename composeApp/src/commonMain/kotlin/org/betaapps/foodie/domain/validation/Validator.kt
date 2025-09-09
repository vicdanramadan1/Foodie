package org.betaapps.foodie.domain.validation

fun interface Validator<T> {
    fun validate(value: T): InputValidationResult
}