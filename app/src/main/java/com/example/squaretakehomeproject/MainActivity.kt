package com.example.squaretakehomeproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import com.example.squaretakehomeproject.adapter.EmployeesAdapter
import com.example.squaretakehomeproject.data.model.appmodel.EmployeeApplicationModel
import com.example.squaretakehomeproject.network.NetworkState
import com.example.squaretakehomeproject.viewmodel.EmployeeViewModel
class MainActivity : AppCompatActivity() {
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var reloadButton: Button
    private lateinit var adapter: EmployeesAdapter
    private lateinit var errorView: View
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.activity_main_employee_list_recyclerview)
        reloadButton = findViewById<Button?>(R.id.activity_main_reload).also {
            it.isEnabled = false
        }
        errorView = findViewById(R.id.activity_main_error)
        progressBar = findViewById(R.id.activity_main_progressBar)

        reloadButton.setOnClickListener {
            it.isEnabled = false
            viewModel.getEmployees()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.employeeFetchState.observe(this) { networkState ->
            progressBar.visibility = INVISIBLE
            when (networkState) {
                is NetworkState.Loading -> progressBar.visibility = VISIBLE
                is NetworkState.Success -> populateResults(networkState.data)
                is NetworkState.EmptyResponse -> showEmptyResultsUI()
                is NetworkState.MalformedResponse -> { /* No-Op*/  }
                is NetworkState.Error -> showErrorUI(networkState)
            }
        }
    }

    private fun populateResults(data: List<EmployeeApplicationModel>) {
       recyclerView.visibility = VISIBLE
        errorView.visibility = INVISIBLE
        if(recyclerView.adapter == null ) {
            adapter = EmployeesAdapter(data)
            recyclerView.adapter = adapter
        } else {
            adapter.updateEmployees(data)
            adapter.notifyItemRangeChanged(0, data.size)
        }
    }

    private fun showEmptyResultsUI() {
        recyclerView.visibility = INVISIBLE
        errorView.visibility = VISIBLE
        errorView.findViewById<TextView>(R.id.error_message_textview).also { it.text = resources.getText(R.string.no_results_found) }
    }

    private fun showErrorUI(error: NetworkState.Error) {
        progressBar.visibility = INVISIBLE
        recyclerView.visibility = INVISIBLE
        errorView.visibility = VISIBLE
        val errorViewMessage = errorView.findViewById<TextView>(R.id.error_message_textview)
        when(error) {
            is NetworkState.UnauthorizedAccess -> errorViewMessage.text = resources.getText(R.string.unauthorized_access)
            is NetworkState.RequestTimeOutError -> errorViewMessage.text = resources.getText(R.string.request_timeout)
            is NetworkState.NetworkUnavailableError -> errorViewMessage.text = resources.getText(R.string.network_unavailable)
            is NetworkState.ServerError -> errorViewMessage.text = resources.getText(R.string.network_unavailable)
            else -> errorViewMessage.text = resources.getText(R.string.unknown_error)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEmployees()
    }
}