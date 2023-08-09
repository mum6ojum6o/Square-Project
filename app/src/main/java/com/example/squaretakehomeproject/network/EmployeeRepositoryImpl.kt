package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeList
import com.example.squaretakehomeproject.network.repository.EmployeeRepository
import io.reactivex.rxjava3.core.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EmployeeRepositoryImpl: EmployeeRepository, KoinComponent {
    private val employeeApi: EmployeeApi by inject<EmployeeApi>()
    override fun getEmployees(): Single<EmployeeList> =
        employeeApi.getEmployees()
}