package com.example.squaretakehomeproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.squaretakehomeproject.R
import com.example.squaretakehomeproject.data.model.appmodel.EmployeeApplicationModel

class EmployeesAdapter(var employees: List<EmployeeApplicationModel>): RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
    fun updateEmployees(latestEmployeeData: List<EmployeeApplicationModel>) {
        employees = latestEmployeeData
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = employees.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.data = employees[position]
        holder.populateUI()
    }

    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val employeeNameTextView: TextView
        val employeeTeamTextView: TextView
        val employeePhotoImageView: ImageView
        lateinit var data: EmployeeApplicationModel
        init {
            employeeNameTextView = itemView.findViewById(R.id.employee_item_name_textview)
            employeeTeamTextView = itemView.findViewById(R.id.employee_item_team_textview)
            employeePhotoImageView = itemView.findViewById(R.id.employee_item_imageview)
        }

        fun populateUI() {
            employeeNameTextView.text = data.fullName
            employeeTeamTextView.text = data.team
            Glide.with(employeePhotoImageView)
                .load(data.photoUrlSmall)
                //.override(200,200)
                .error(R.drawable.baseline_broken_image_64)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.baseline_person_64)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(employeePhotoImageView)
        }
    }
}