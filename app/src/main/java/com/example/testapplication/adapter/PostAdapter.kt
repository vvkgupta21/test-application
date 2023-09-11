package com.example.testapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.PostItemsBinding
import com.example.testapplication.model.PostDataItem

class PostDataAdapter(private var getList: ArrayList<PostDataItem>) : RecyclerView.Adapter<PostDataAdapter.Holder>() {

    class Holder(val binding: PostItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(PostItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = getList[position]
        holder.binding.model = model
    }

    override fun getItemCount() = getList.size

    fun initList(initialList: ArrayList<PostDataItem>) {
        getList = initialList
        notifyDataSetChanged()
    }
}