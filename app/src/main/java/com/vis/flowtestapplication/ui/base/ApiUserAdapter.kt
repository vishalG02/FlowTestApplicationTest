package com.vis.flowtestapplication.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vis.flowtestapplication.R
import com.vis.flowtestapplication.data.local.entity.User
import com.vis.flowtestapplication.data.model.ApiUser
import com.vis.flowtestapplication.databinding.ItemLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class ApiUserAdapter(
    private val users: ArrayList<ApiUser>
) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {


    class DataViewHolder(context: Context, val binding:ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ApiUser) {
            binding. textViewUserName.text = user.name
            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context)
                .load(user.avatar)
                .into(binding.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DataViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(parent.context, binding)
    }
    /*  DataViewHolder(
              LayoutInflater.from(parent.context).inflate(
                  R.layout.item_layout, parent,
                  false
              )
          )*/

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<ApiUser>) {
        users.addAll(list)
    }

}