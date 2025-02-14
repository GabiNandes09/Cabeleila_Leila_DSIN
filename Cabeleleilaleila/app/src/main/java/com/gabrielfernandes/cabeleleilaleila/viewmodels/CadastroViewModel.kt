package com.gabrielfernandes.cabeleleilaleila.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.cabeleleilaleila.models.User
import com.gabrielfernandes.cabeleleilaleila.networkRepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmation = MutableStateFlow("")
    val confirmation = _confirmation.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _passwordDoesntMatch = MutableStateFlow(false)
    val passwordDoenstMatch = _passwordDoesntMatch.asStateFlow()

    private val _complete = MutableStateFlow(false)
    val complete = _complete.asStateFlow()

    fun cadastrar(){
        if (_name.value.isEmpty() ||
            _email.value.isEmpty() ||
            _password.value.isEmpty() ||
            _confirmation.value.isEmpty()
            ){
            _hasError.value = true
            return
        }

        if(_password.value != _confirmation.value){
            _passwordDoesntMatch.value = true
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val user = User(
                username = _name.value,
                password = _password.value,
                email = _email.value
            )
            try {
                userRepository.insert(user)
                _complete.value = true
            } catch (e: Exception){
                println(e.message)
                _hasError.value = true
            }

        }

    }

    fun onOkayClick(){
        _hasError.value = false
        _passwordDoesntMatch.value = false
    }

    fun setName(name: String){
        _name.value = name
    }

    fun setEmail(email: String){
        _email.value = email
    }

    fun setPassword(password: String){
        _password.value = password
    }

    fun setConfirmation(confirmation: String){
        _confirmation.value = confirmation
    }
}