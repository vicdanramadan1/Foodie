package org.betaapps.foodie.presentation.profile

import org.betaapps.foodie.data.model.User

sealed class ProfileScreenEvent {
    data class EditUserInfo(val user: User): ProfileScreenEvent()
    data object DismissAlertDialog : ProfileScreenEvent()
}