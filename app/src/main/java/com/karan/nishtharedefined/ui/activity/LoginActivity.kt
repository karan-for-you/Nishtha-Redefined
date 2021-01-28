package com.karan.nishtharedefined.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.const.ErrorConstants
import com.karan.nishtharedefined.databinding.ActivityLoginBinding
import com.karan.nishtharedefined.ui.fragment.home.HomeViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var bindingLoginActivity : ActivityLoginBinding
    private val emailRegex = Regex(AppConstants.EMAIL_REGEX)
    private val prdRegex  = Regex(AppConstants.PRD_REGEX)

    private val loginViewModel by lazy {
        ViewModelProvider(this,LoginViewModel.Factory(application)).get(
            LoginViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLoginActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )

        //initObservers()
        bindingLoginActivity.etPassword.addTextChangedListener(passwordListener)
        bindingLoginActivity.etEmail.addTextChangedListener(emailListener)
        bindingLoginActivity.btnLogin.setOnClickListener {
            val emailError = bindingLoginActivity.tvEmailError.text.toString().trim()
            val passwordError = bindingLoginActivity.tvPasswordError.text.toString().trim()
            if(bindingLoginActivity.etEmail.text.toString().trim().isNotEmpty() &&
                bindingLoginActivity.etPassword.text.toString().trim().isNotEmpty()
            ) {
                if (emailError.isEmpty() && passwordError.isEmpty())
                    startActivity(Intent(this, Logged::class.java))
                else
                    Snackbar.make(
                        bindingLoginActivity.root,
                        "Please resolve the errors",
                        Snackbar.LENGTH_SHORT
                    ).show()
            }else Snackbar.make(
                bindingLoginActivity.root,
                "Please enter both Email and Password",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers(){
        loginViewModel.emailValidator.observe(this,
            Observer<String> { t ->
                when(t){
                    ErrorConstants.EMPTY -> bindingLoginActivity.tvEmailError.text =
                        "Email can't be empty"
                    ErrorConstants.LENGTH ->
                        bindingLoginActivity.tvEmailError.text =
                            "Email is too short"
                    ErrorConstants.INVALID_EMAIL -> bindingLoginActivity.tvEmailError.text =
                        "Name contains invalid character"
                    else -> bindingLoginActivity.tvEmailError.text = ""
                } })

        loginViewModel.passwordValidator.observe(this,
            Observer<String> { t ->
                when (t) {
                    ErrorConstants.EMPTY -> bindingLoginActivity.tvPasswordError.text =
                        "Password can't be empty"
                    ErrorConstants.LENGTH -> bindingLoginActivity.tvPasswordError.text =
                        "Password should be of minimum 8 characters"
                    ErrorConstants.INVALID_PASSWORD -> bindingLoginActivity.tvPasswordError.text =
                        "Password must contain a capital letter, a special character and a number "
                    else -> bindingLoginActivity.tvPasswordError.text = ""
                }
            })
    }

    private val emailListener =object : TextWatcher{
        override fun afterTextChanged(editable: Editable?) {
            val email = editable.toString()
            bindingLoginActivity.tvEmailError.text = when {
                email.trim().isEmpty() -> "Email can't be empty"
                email.trim().length < 5 -> "Email is too short"
                !(email.trim().matches(emailRegex)) -> "Invalid Email"
                else -> ""
            }
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val passwordListener = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val password = editable.toString()
            bindingLoginActivity.tvPasswordError.text = when {
                password.trim().isEmpty() -> "Password can't be empty"
                password.trim().length < 8 -> "Password should be of minimum 8 characters"
                !(password.trim().matches(prdRegex)) -> "Password must contain a capital letter, a special character and a number "
                else -> ""
            }
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }
}