package com.example.squaretakehomeproject.data.datasource

import com.example.squaretakehomeproject.data.model.appmodel.EmployeeApplicationModel
import com.example.squaretakehomeproject.network.repository.EmployeeRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EmployeeDataSource: KoinComponent {
    private val employeeRepository: EmployeeRepository by inject()

    fun getEmployees(): Single<List<EmployeeApplicationModel>> {
        return employeeRepository.getEmployees()
            .subscribeOn(Schedulers.io())
            .map {employeesFromNetwork ->
                employeesFromNetwork.employees.map {
                    if(it.isValid()) {
                        EmployeeApplicationModel(
                            uuid = it.uuid!!,
                            fullName = it.fullName ?: "",
                            phoneNumber = it.phoneNumber,
                            email = it.email ?: "",
                            photoUrlSmall = it.photoUrlSmall,
                            photoUrlLarge = it.photoUrlLarge,
                            team = it.team ?: ""
                        )
                    }
                    else throw IllegalStateException()
                }
            }
    }
}