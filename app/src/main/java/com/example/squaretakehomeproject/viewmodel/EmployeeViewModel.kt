package com.example.squaretakehomeproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squaretakehomeproject.data.datasource.EmployeeDataSource
import com.example.squaretakehomeproject.data.model.appmodel.EmployeeApplicationModel
import com.example.squaretakehomeproject.network.NetworkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalStateException
import java.util.concurrent.TimeoutException

class EmployeeViewModel: ViewModel(), KoinComponent {
    val dataSource: EmployeeDataSource by inject<EmployeeDataSource>()

    private val _employeesResultState: MutableLiveData<NetworkState<List<EmployeeApplicationModel>>> = MutableLiveData()
    val employeeFetchState: LiveData<NetworkState<List<EmployeeApplicationModel>>>
        get() = _employeesResultState

    fun getEmployees(){
        _employeesResultState.value = NetworkState.Loading
        dataSource.getEmployees()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::handleResponse,
                ::handleErrors
            )
    }
    private fun handleResponse(employees: List<EmployeeApplicationModel>) {
        if(employees.isEmpty()){
            _employeesResultState.value = NetworkState.EmptyResponse
        } else {
            _employeesResultState.value = NetworkState.Success(employees)
        }
    }
    private fun handleErrors(throwable: Throwable) {
        _employeesResultState.value = when (throwable){
            is IOException -> NetworkState.NetworkUnavailableError(throwable.message ?: "")
            is TimeoutException -> NetworkState.RequestTimeOutError(throwable.message ?: "")
            is HttpException -> handleHttpErrors(throwable)
            is IllegalStateException -> NetworkState.MalformedResponse
            else -> { NetworkState.UnknownError(throwable.message ?: "")}
        }
    }

    private fun handleHttpErrors(throwable: HttpException): NetworkState.Error {
        return if(throwable.code() == 401) {
            NetworkState.UnauthorizedAccess(throwable.message ?: "")
        } else if(throwable.code() in 500..599) {
            NetworkState.ServerError(throwable.message ?: "")
        } else NetworkState.UnknownError(throwable.message ?: "")
    }
}