package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EmployeeApi {
    @GET("/sq-mobile-interview/employees.json")
    fun getEmployees(): Single<EmployeeList>
}