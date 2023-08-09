package com.example.squaretakehomeproject.network.repository

import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeList
import io.reactivex.rxjava3.core.Single

interface EmployeeRepository {
    fun getEmployees(): Single<EmployeeList>
}