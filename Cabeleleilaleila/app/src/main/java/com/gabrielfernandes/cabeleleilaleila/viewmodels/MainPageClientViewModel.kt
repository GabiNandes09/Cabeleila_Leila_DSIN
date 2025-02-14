package com.gabrielfernandes.cabeleleilaleila.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielfernandes.cabeleleilaleila.UserPreferences
import com.gabrielfernandes.cabeleleilaleila.models.Scheduling
import com.gabrielfernandes.cabeleleilaleila.models.Service
import com.gabrielfernandes.cabeleleilaleila.models.User
import com.gabrielfernandes.cabeleleilaleila.networkRepository.SchedulingRepository
import com.gabrielfernandes.cabeleleilaleila.networkRepository.ServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class MainPageClientViewModel(
    private val serviceRepository: ServiceRepository,
    val userPreferences: UserPreferences,
    private val schedulingRepository: SchedulingRepository
) : ViewModel() {
    private val _listService = MutableStateFlow<List<Service>>(emptyList())
    val listService = _listService.asStateFlow()

    private val _selectedDate = MutableStateFlow("")

    private var selectedServices = mutableMapOf<Int, Service>()

    private val _total = MutableStateFlow(0.0)
    val total = _total.asStateFlow()

    private val _hour = MutableStateFlow("")
    val hour = _hour.asStateFlow()

    private val _clean = MutableStateFlow(false)
    val clean = _clean.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadServices()
        }

    }

    private suspend fun loadServices() {
        _listService.value = serviceRepository.getAll().body() ?: emptyList()
    }

    fun addService(service: Service, index: Int) {
        if (selectedServices.containsValue(service)) return

        selectedServices[index] = service
        _total.value += service.price
    }

    fun eraseService(index: Int) {
        val service = selectedServices[index]
        service?.let {
            _total.value -= it.price
        }
        selectedServices.remove(index)
    }

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun setHour(hour: Int, minute: Int) {
        val time = "${hour}:${minute}"
        _hour.value = time
    }

    fun finalize(user: User) {
        if (selectedServices.isEmpty() || _total.value == 0.0) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val scheduling = Scheduling(
                user = user,
                date = _selectedDate.value,
                hour = _hour.value,
                services = selectedServices.map { it.value }
            )

            schedulingRepository.insert(scheduling)
            cleanScheduling()
        }

    }

    private fun cleanScheduling(){
        _selectedDate.value = ""
        _hour.value = ""
        _total.value = 0.0
        selectedServices = mutableMapOf()
        _clean.value = true
    }

    fun finishClean(){
        _clean.value = false
    }
}