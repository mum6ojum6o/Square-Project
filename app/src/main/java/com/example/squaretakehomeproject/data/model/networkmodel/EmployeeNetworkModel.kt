package com.example.squaretakehomeproject.data.model.networkmodel

import com.google.gson.annotations.SerializedName


data class EmployeeList(
    val employees:List<EmployeeNetworkModel>
)

data class EmployeeNetworkModel(
    val uuid: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("email_address") val email: String?,
    @SerializedName("photo_url_small") val photoUrlSmall: String?,
    @SerializedName("photo_url_large") val photoUrlLarge: String?,
    val team: String?
) {
    fun isValid(): Boolean {
        return  !(uuid.isNullOrBlank() ||
                fullName.isNullOrBlank() ||
                email.isNullOrBlank() ||
                team.isNullOrBlank())
    }
}
