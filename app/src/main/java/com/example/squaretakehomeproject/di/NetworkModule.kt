package com.example.squaretakehomeproject.di


import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.squaretakehomeproject.data.datasource.EmployeeDataSource
import com.example.squaretakehomeproject.network.EmployeeApi
import com.example.squaretakehomeproject.network.EmployeeRepositoryImpl
import com.example.squaretakehomeproject.network.repository.EmployeeRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DEFAULT_OKHTTP_CLIENT = "Default_OkHttpClient"
const val BASE_URL = "https://s3.amazonaws.com/"
val networkModule = module {
    single<OkHttpClient>(named(DEFAULT_OKHTTP_CLIENT)){
        OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .addInterceptor(
                ChuckerInterceptor.Builder(get())
                .build()
            )
            .build()
    }
    single<Retrofit> {
        val gson = GsonBuilder().create()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get(named(DEFAULT_OKHTTP_CLIENT)))
            .build()
    }

    single<EmployeeApi>{
        get<Retrofit>().create(EmployeeApi::class.java)
    }

    single<EmployeeDataSource> {
        EmployeeDataSource()
    }

    single<EmployeeRepository>{
        EmployeeRepositoryImpl()
    }
}