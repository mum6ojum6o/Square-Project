package com.example.squaretakehomeproject.data.model.appmodel

data class EmployeeApplicationModel(
    val uuid: String,
    val fullName: String,
    val phoneNumber: String?,
    val email: String,
    val photoUrlSmall: String?,
    val photoUrlLarge: String?,
    val team: String,
)
