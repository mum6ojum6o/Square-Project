package com.example.squaretakehomeproject

import com.example.squaretakehomeproject.data.model.networkmodel.EmployeeNetworkModel
import org.junit.Assert
import org.junit.Test

class EmployeeNetworkModelValidityTest {
    @Test
    fun `when uuid Is Null NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = null,
            fullName = "somename",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = "sdflg"
            )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }
    @Test
    fun `when uuid Is empty NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "",
            fullName = "somename",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = "sdflg"
        )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }

    @Test
    fun `when fullname Is empty NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "sdfsdf34354",
            fullName = "",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = "sdflg"
        )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }

    @Test
    fun `when fullname Is NULL NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "sdfsdf34354",
            fullName = null,
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = "sdflg"
        )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }

    @Test
    fun `when team Is NULL NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "sdfsdf34354",
            fullName = "James B",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = null
        )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }

    @Test
    fun `when team Is empty NetworkModel Is Invalid`() {
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "sdfsdf34354",
            fullName = "James B",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = ""
        )
        Assert.assertFalse(employeeNetworkModel.isValid())
    }

    @Test
    fun `when fullname uuid team are not null or blank the networkmodel is valid`(){
        val employeeNetworkModel = EmployeeNetworkModel(uuid = "sdfsdf34354",
            fullName = "James B",
            phoneNumber = "9999999999",
            email = "asfjlsdfgsa",
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = "sadasd"
        )
        Assert.assertTrue(employeeNetworkModel.isValid())
    }
    /*Could have added more tests for phoneNumber and email but ran sort of time.*/
}