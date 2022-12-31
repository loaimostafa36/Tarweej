package com.example.tarweej.ui.login

interface MainLoginContract {
    interface View {

        fun showProgress()
        fun hideProgress()
        fun setEmailOrPhoneOrPasswordError(emailOrPhone: Int,Or: Int,password: Int)
        fun setInitialEdtEmailAndPhoneAndPasswordHint()
        fun changeEdtEmailAndPhoneHint()
        fun onLoginSuccess()
        fun emptyEditText()
        fun checkRadioButton():Boolean
        fun getEmailEnteredByUser() : String
        fun getPhoneEnteredByUser() : String
        fun getPasswordEnteredByUser() : String
    }

    interface Presenter {

        fun loginButtonClicked(emailOrPhone: String, password: String)
        fun changeHintByRadioButton()
        fun checkPhoneNumber(phone: String): Boolean
        fun checkEmail(email: String): Boolean

    }

}