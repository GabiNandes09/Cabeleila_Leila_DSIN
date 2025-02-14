package com.gabrielfernandes.cabeleleilaleila.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.cabeleleilaleila.UserPreferences
import kotlinx.coroutines.launch

class MainViewModel(
    val userPreferences: UserPreferences
) : ViewModel() {

    fun logout(){
        viewModelScope.launch {
            userPreferences.clearUser()
        }
    }
}