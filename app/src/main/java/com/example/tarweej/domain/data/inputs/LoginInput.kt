package com.example.tarweej.domain.data.inputs

import com.apollographql.apollo3.api.Optional
import com.example.tarweej.domain.data.models.DeviceEnum

data class LoginInput(
    var phone: String ="+2001012345678",
    val email:String?=null,
    val password: String = "123456",
    val device: DeviceEnum = DeviceEnum.ANDROID
)