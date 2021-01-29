package com.karan.nishtharedefined.ui.activity

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.*
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.const.ErrorConstants
import java.util.regex.Pattern

class LoginViewModel(app : Application) :AndroidViewModel(app){

    var email = ""
    var password = ""
    var emailError = ""
    var passwordError = ""

    private val characterRegex = Pattern.compile(AppConstants.SPECIAL_CHAR_REGEX)
    private val numberRegex = Regex(AppConstants.NUMBER_REGEX)
    private val emailRegex = Regex(AppConstants.EMAIL_REGEX)
    private val prdRegex  = Regex(AppConstants.PRD_REGEX)


    private var _emailValidator = MutableLiveData<String>()
    val emailValidator : LiveData<String>
        get() = _emailValidator

    private var _passwordValidator = MutableLiveData<String>()
    val passwordValidator : LiveData<String>
        get() = _passwordValidator


    val emailWatcher = object : TextWatcher{
        override fun afterTextChanged(editable: Editable?) {
            email = editable.toString()
            when {
                email.trim().isEmpty() -> _emailValidator.value = ErrorConstants.EMPTY
                email.trim().length < 5 -> _emailValidator.value = ErrorConstants.LENGTH
                !(email.trim().matches(emailRegex)) -> _emailValidator.value = ErrorConstants.INVALID_EMAIL
                else -> _emailValidator.value = ErrorConstants.NO_ERROR
            }
            emailError = _emailValidator.value.toString()
            //checkButtonUIValidation(nameError,emailError,passwordError)
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    val passwordListener = object : TextWatcher{
        override fun afterTextChanged(editable: Editable?) {
            password = editable.toString()
            when {
                password.trim().isEmpty() -> _passwordValidator.value = ErrorConstants.EMPTY
                password.trim().length < 8 -> _passwordValidator.value = ErrorConstants.LENGTH
                !(password.trim().matches(prdRegex)) -> _passwordValidator.value = ErrorConstants.INVALID_PASSWORD
                else -> _passwordValidator.value = ErrorConstants.NO_ERROR
            }
            passwordError = _passwordValidator.value.toString()
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(LoginViewModel::class.java))
                return LoginViewModel(app) as T
            throw IllegalAccessException("Unable to Create Login View Model")
        }

    }
}