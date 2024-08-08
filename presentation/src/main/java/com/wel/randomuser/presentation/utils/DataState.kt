package com.wel.randomuser.presentation.utils

import com.wel.randomuser.domain.models.User


sealed class DataState : UiAwareModel() {
    object Loading : DataState()
    data class Error(var error: String = "") : DataState()
    data class Success(val data: List<User>) : DataState()
}