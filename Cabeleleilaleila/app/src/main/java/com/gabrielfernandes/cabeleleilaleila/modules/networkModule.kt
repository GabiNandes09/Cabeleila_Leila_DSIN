package com.gabrielfernandes.cabeleleilaleila.modules

import com.gabrielfernandes.cabeleleilaleila.ip.IP
import com.gabrielfernandes.cabeleleilaleila.networkRepository.SchedulingRepository
import com.gabrielfernandes.cabeleleilaleila.networkRepository.ServiceRepository
import com.gabrielfernandes.cabeleleilaleila.networkRepository.UserRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://$IP/api/v1/"

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(UserRepository::class.java) }
    single { get<Retrofit>().create(ServiceRepository::class.java) }
    single { get<Retrofit>().create(SchedulingRepository::class.java) }
}