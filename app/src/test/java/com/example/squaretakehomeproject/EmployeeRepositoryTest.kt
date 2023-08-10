package com.example.squaretakehomeproject

import com.example.squaretakehomeproject.network.EmployeeApi
import com.example.squaretakehomeproject.network.EmployeeRepositoryImpl
import com.example.squaretakehomeproject.network.repository.EmployeeRepository
import com.example.squaretakehomeproject.utils.emptyEmployeesResponse
import com.example.squaretakehomeproject.utils.malformedEmployeeResponse
import com.example.squaretakehomeproject.utils.validEmployeeResponse
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rules.KoinGraphRule

class EmployeeRepositoryTest: KoinComponent {
    @get:Rule
    val mockWebServer: MockWebServer = MockWebServer()

    val koinGraphRule = KoinGraphRule(
        listOf(
            module {
                single<Retrofit> {
                    val gson = GsonBuilder().create()
                    Retrofit.Builder()
                        .baseUrl(mockWebServer.url("/"))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .client(OkHttpClient.Builder().build())
                        .build()
                }
                single<EmployeeApi> {
                    val retrofit: Retrofit = get()
                    retrofit.create(EmployeeApi::class.java)
                }
                single<EmployeeRepository> {
                    EmployeeRepositoryImpl()
                }
            }
        )
    )

    @Test
    fun `when server returns valid employees response then repo emits a single`() {
        mockWebServer.enqueue(MockResponse().setBody(validEmployeeResponse))
        val employeeRepository: EmployeeRepository = get()
        employeeRepository.getEmployees().subscribeOn(Schedulers.io()).blockingSubscribe { employeesList ->
            Assert.assertEquals(2, employeesList.employees.size )
        }
    }

    @Test
    fun `when employees list is empty then repo emits single` () {
        mockWebServer.enqueue(MockResponse().setBody(emptyEmployeesResponse))
        val employeeRepository: EmployeeRepository = get()
        employeeRepository.getEmployees().subscribeOn(Schedulers.io()).test().assertEmpty()
    }

    @Test
    fun `when response contains malformed employee data then repo emits a single`() {
        mockWebServer.enqueue(MockResponse().setBody(malformedEmployeeResponse))
        val employeeRepository: EmployeeRepository = get()
        employeeRepository.getEmployees().subscribeOn(Schedulers.io()).blockingSubscribe {
            Assert.assertEquals(1, it.employees.size)
        }
    }
}