package com.gabrielfernandes.cabeleleilaleila.modules

import com.gabrielfernandes.cabeleleilaleila.UserPreferences
import com.gabrielfernandes.cabeleleilaleila.viewmodels.CadastroViewModel
import com.gabrielfernandes.cabeleleilaleila.viewmodels.LoginViewModel
import com.gabrielfernandes.cabeleleilaleila.viewmodels.MainPageClientViewModel
import com.gabrielfernandes.cabeleleilaleila.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(
            userRepository = get(),
            userPreferences = get()
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

    single {
        UserPreferences(androidContext())
    }

    viewModel {
        MainViewModel(
            userPreferences = get()
        )
    }
}