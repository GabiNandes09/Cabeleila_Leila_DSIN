package com.gabrielfernandes.cabeleleilaleila.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.cabeleleilaleila.models.Service
import com.gabrielfernandes.cabeleleilaleila.networkRepository.ServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    private val _listService = MutableStateFlow<List<Service>>(emptyList())
    val listService = _listService.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadServices()
        }

    }

    private suspend fun loadServices(){
        _listService.value = serviceRepository.getAll().body() ?: emptyList()
    }
}