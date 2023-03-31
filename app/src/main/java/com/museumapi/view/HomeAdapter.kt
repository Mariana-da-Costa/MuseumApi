package com.museumapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.museumapi.databinding.ItemHomeLayoutBinding

class HomeAdapter(val list: List<Int>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemHomeLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}