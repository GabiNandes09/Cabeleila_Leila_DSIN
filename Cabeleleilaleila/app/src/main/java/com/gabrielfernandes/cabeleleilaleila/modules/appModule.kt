package com.gabrielfernandes.cabeleleilaleila.modules

import com.gabrielfernandes.cabeleleilaleila.viewmodels.CadastroViewModel
import com.gabrielfernandes.cabeleleilaleila.viewmodels.LoginViewModel
import com.gabrielfernandes.cabeleleilaleila.viewmodels.MainPageClientViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(
            userRepository = get()
        )
    }

    viewModel {
        CadastroViewModel(
            userRepository = get()
        )
    }
    viewModel {
        MainPageClientViewModel(
            serviceRepository = get()
        )
    }
}