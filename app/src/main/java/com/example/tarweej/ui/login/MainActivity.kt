package com.example.tarweej.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tarweej.R
import com.example.tarweej.ui.advertisementList.SecondActivity
import com.example.tarweej.databinding.ActivityMainBinding
import com.example.tarweej.domain.interactors.LoginInteractor
import com.example.tarweej.domain.interactors.LoginInteractorImp
import com.example.tarweej.domain.sherPref.SharedPrefHelper
import java.util.regex.Pattern


class MainActivity : AppCompatActivity(), MainLoginContract.View {


    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefHelper: SharedPrefHelper
    private lateinit var  presenter : MainLoginPresenter
    private lateinit var loginInteractor: LoginInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginInteractor = LoginInteractorImp(this)

        presenter = MainLoginPresenter(this, loginInteractor )
        sharedPrefHelper = SharedPrefHelper(this)

        presenter.changeHintByRadioButton()

        binding.btnSignin.setOnClickListener { presenter.loginButtonClicked(getPhoneEnteredByUser(),getPasswordEnteredByUser()) }
    }

    override fun onResume() {
        super.onResume()
        setInitialEdtEmailAndPhoneAndPasswordHint()
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }




    override fun checkRadioButton():Boolean{
        return binding.radioPhone.isChecked
    }

    override fun getEmailEnteredByUser(): String {
        return binding.edtPhoneAndEmail.text.toString()
    }

    override fun getPhoneEnteredByUser(): String {
        return binding.edtPhoneAndEmail.text.toString()
    }

    override fun getPasswordEnteredByUser(): String {
        return binding.edtPassword.text.toString()
    }

    override fun showProgress() {
        binding.progressbarLogin.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressbarLogin.visibility = View.GONE
    }

    override fun setEmailOrPhoneOrPasswordError(emailOrPhone: Int,Or : Int,password: Int) {
        Toast.makeText(this, "${getString(emailOrPhone)} ${getString(Or)} ${getString(password)}", Toast.LENGTH_LONG).show()
    }

    override fun onLoginSuccess(){

        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
    override fun emptyEditText(){
        binding.edtPhoneAndEmail.setText("")
        binding.edtPassword.setText("")
    }

    override fun setInitialEdtEmailAndPhoneAndPasswordHint() {
        binding.layoutPhoneAndEmail.setHint(R.string.phone_number)
        binding.layoutPassword.setHint(R.string.password)
    }
    override fun changeEdtEmailAndPhoneHint() {
        binding.radioGroupOr.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_email -> {
                    binding.layoutPhoneAndEmail.setHint(R.string.email)
                }
                R.id.radio_phone -> {
                    binding.layoutPhoneAndEmail.setHint(R.string.phone_number)
                }
            }
        }
    }

}



