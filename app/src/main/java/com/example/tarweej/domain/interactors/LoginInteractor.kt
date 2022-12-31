package com.example.tarweej.domain.interactors

import com.example.tarweej.domain.data.inputs.LoginInput
import com.example.tarweej.domain.data.models.User

interface LoginInteractor {
   suspend fun login(loginInput: LoginInput)
}