package com.example.tarweej.domain.mappers

import com.example.rocketreserver.PhoneAndEmailLoginMutation
import com.example.tarweej.domain.data.models.User

object LoginMapper {
    fun convertToUser(data1: PhoneAndEmailLoginMutation.Data1): User  {
        return User(
            id = data1.id,
            email = data1.email,
            verifiedPhone = data1.verifiedPhone,
            token = data1.token
        )
    }
}