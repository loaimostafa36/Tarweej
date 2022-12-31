package com.example.tarweej.domain.interactors

import android.content.Context
import android.util.Log
import apolloClient
import com.example.rocketreserver.PhoneAndEmailLoginMutation
import com.example.tarweej.domain.AppResult
import com.example.tarweej.domain.data.inputs.LoginInput
import com.example.tarweej.domain.mappers.InputLoginMapper
import com.example.tarweej.domain.sherPref.SharedPrefHelper


class LoginInteractorImp(private val context : Context) : LoginInteractor {
    private var  sharedPrefHelper : SharedPrefHelper = SharedPrefHelper(context)
    private var token : String? = ""


    override suspend fun login(loginInput: LoginInput){
        val response = try {
            apolloClient(context).mutation(PhoneAndEmailLoginMutation(InputLoginMapper.convertToPhoneAndEmailLoginInput(loginInput))).execute()
        } catch (e: Exception) {
            null
        }

        if (response?.hasErrors() != true) {
            token = response?.data?.phoneAndEmailLogin?.data?.token
            Log.d("token", token.toString())
            sharedPrefHelper.put("token", token ?: "")
        }
    }
}