package com.example.squaretakehomeproject

import com.example.squaretakehomeproject.data.datasource.EmployeeDataSource
import com.example.squaretakehomeproject.data.model.appmodel.EmployeeApplicationModel
import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeList
import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeNetworkModel
import com.example.squaretakehomeproject.network.EmployeeApi
import com.example.squaretakehomeproject.network.repository.EmployeeRepository
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rules.KoinGraphRule

class EmployeeDataSourceTest: KoinComponent {
    @get:Rule
    val mockWebServer: MockWebServer = MockWebServer()
    lateinit var employeeDataSource: EmployeeDataSource
    lateinit var employeeRepository: EmployeeRepository
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
                    mock()
                }
                single<EmployeeRepository> {
                    mock()
                }
            }
        )
    )

    @Before
    fun before() {
        employeeRepository = get()
        employeeDataSource = EmployeeDataSource()
    }

    @Test
    fun `when emplpoyees fetched emit success` () {
        val validResponseList = listOf<EmployeeApplicationModel>(
            EmployeeApplicationModel(uuid="sadasdas",
                "Arjan S",
                null,
                "arjan@ssdf.com",
                null,null,"good team"
            )
        )

        val employeeList = EmployeeList(
            listOf(EmployeeNetworkModel(
                uuid="sadasdas",
                "Arjan S",
                null,
                "arjan@ssdf.com",
                null,null,"good team")
            )
        )

        whenever(employeeRepository.getEmployees()).thenReturn(Single.just(employeeList))

        employeeDataSource.getEmployees().subscribeOn(Schedulers.io()).blockingSubscribe { employeesResponse ->
            Assert.assertEquals(validResponseList.size,employeesResponse.size)
            Assert.assertEquals(validResponseList[0],employeesResponse[0])
            Assert.assertEquals(validResponseList[1],employeesResponse[1])
        }
    }

    @Test
    fun `when no Employees fetched emit No Response state` () {
        val emptyEmployeeList = EmployeeList(
            listOf()
        )
        whenever(employeeRepository.getEmployees()).thenReturn(Single.just(emptyEmployeeList))
        val single = employeeDataSource.getEmployees().subscribeOn(Schedulers.io())
        val observer = single.test()
        observer.assertEmpty()
    }
    /*
    @Test
    fun `when Employee is malformed throw illegatStateException`() {
        val malformedEmployeeList = EmployeeList(
            listOf(
                EmployeeNetworkModel(
                    uuid="sadasdas",
                    "Arjan S",
                    null,
                    "arjan@ssdf.com",
                    null,null,
                    null
                )
            )
        )
        whenever(employeeRepository.getEmployees()).thenReturn(Single.just(malformedEmployeeList))
        val thrown = Assert.assertThrows(IllegalStateException::class.java) {
            employeeDataSource.getEmployees().subscribeOn(Schedulers.io()).blockingSubscribe {}
        }

        val expectedMessage = ""
        val actualMessage = thrown.message
        Assert.assertEquals(expectedMessage,actualMessage)
    }
    */

}
