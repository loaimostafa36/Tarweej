package com.example.tarweej.ui.login


import com.example.tarweej.R
import com.example.tarweej.domain.data.inputs.LoginInput
import com.example.tarweej.domain.data.models.DeviceEnum
import com.example.tarweej.domain.interactors.LoginInteractor
import kotlinx.coroutines.*
import java.util.regex.Pattern

class MainLoginPresenter(
    private val iLoginView: MainLoginContract.View?,
    private val loginInteractor: LoginInteractor
) : MainLoginContract.Presenter {
    private var loginInput: LoginInput = LoginInput("+2001012345678",null, "123456", DeviceEnum.ANDROID)
    override fun changeHintByRadioButton() {
        iLoginView?.setInitialEdtEmailAndPhoneAndPasswordHint()
        iLoginView?.changeEdtEmailAndPhoneHint()
    }
    override fun loginButtonClicked(emailOrPhone: String, password: String) {
        if (iLoginView?.checkRadioButton() == true) {
            if (checkPhoneNumber(emailOrPhone) && emailOrPhone==loginInput.phone && password == loginInput.password) {
                iLoginView?.showProgress()
                // launch doesn't require suspend keyword
                CoroutineScope(context = Dispatchers.IO).launch {
                    loginInteractor.login(loginInput)
                    withContext(Dispatchers.Main) {
                        iLoginView?.hideProgress()
                        iLoginView?.onLoginSuccess()
                        iLoginView?.emptyEditText()
                    }

                }
            } else {
                iLoginView?.setEmailOrPhoneOrPasswordError(R.string.invalid_phone,R.string.or,R.string.invalid_pass)
            }
        } else {
            if (checkEmail(emailOrPhone) && emailOrPhone == loginInput.email && password == loginInput.password) {
                iLoginView?.showProgress()
                CoroutineScope(context = Dispatchers.IO).launch {
                    loginInteractor.login(loginInput)
                    withContext(Dispatchers.Main) {
                        iLoginView?.hideProgress()
                        iLoginView?.onLoginSuccess()
                        iLoginView?.emptyEditText()
                    }
                }
            } else {
                iLoginView?.setEmailOrPhoneOrPasswordError(R.string.invalid_email,R.string.or,R.string.invalid_pass)
            }
        }

    }


    override fun checkPhoneNumber(phone: String): Boolean {
        return phonePattern.matcher(phone).matches()
    }

    override fun checkEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    private val  emailPattern: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    private val phonePattern: Pattern = Pattern.compile(
        "^\\+[0-9]{10,13}\$"
    )
}