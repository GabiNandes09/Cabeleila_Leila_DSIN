package com.gabrielfernandes.cabeleleilaleila.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.cabeleleilaleila.UserPreferences
import com.gabrielfernandes.cabeleleilaleila.networkRepository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _logged = MutableStateFlow(false)
    val logged = _logged.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    fun login(){
        if (_email.value.isEmpty() || _password.value.isEmpty()){
            _hasError.value = true
            return
        }

        viewModelScope.launch {
            val users = userRepository.getAll().body()
            val checkedUser = users?.find { user ->
                user.email.equals(_email.value, ignoreCase = true) &&
                        user.password.equals(_password.value)
            }

            if (checkedUser == null){
                _hasError.value = true
            } else {
                userPreferences.saveUser(
                    id = checkedUser.id.toString(),
                    username = checkedUser.username,
                    email = checkedUser.email,
                    password = checkedUser.password
                )
                _logged.value = true
            }


        }
    }



    fun onOkayClick(){
        _hasError.value = false
    }

    fun setEmail(email: String){
        _email.value = email
    }

    fun setPassword(password: String){
        _password.value = password
    }
}