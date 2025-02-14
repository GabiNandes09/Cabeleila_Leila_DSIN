package com.gabrielfernandes.cabeleleilaleila.modules

import com.gabrielfernandes.cabeleleilaleila.viewmodels.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(
            userRepository = get()
        )
    }
}