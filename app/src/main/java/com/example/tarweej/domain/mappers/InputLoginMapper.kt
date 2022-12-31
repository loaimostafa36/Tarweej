package com.example.tarweej.domain.mappers

import com.apollographql.apollo3.api.Optional
import com.example.rocketreserver.type.PhoneAndEmailLoginInput
import com.example.tarweej.domain.data.inputs.LoginInput
import com.example.tarweej.domain.data.models.DeviceEnum

object InputLoginMapper {

    fun convertToPhoneAndEmailLoginInput(loginInput: LoginInput): PhoneAndEmailLoginInput {
        return PhoneAndEmailLoginInput(
            phone = Optional.present(loginInput.phone),
            password = loginInput.password,
            device = getDeviceEnum(loginInput.device)

        )

    }

    private fun getDeviceEnum(device: DeviceEnum): com.example.rocketreserver.type.DeviceEnum {
        return when (device) {
            DeviceEnum.ANDROID -> com.example.rocketreserver.type.DeviceEnum.ANDROID
            DeviceEnum.DESKTOP -> com.example.rocketreserver.type.DeviceEnum.DESKTOP
            else -> com.example.rocketreserver.type.DeviceEnum.IOS
        }

    }


}

