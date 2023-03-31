package com.museumapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.museumapi.databinding.ItemHomeLayoutBinding
import com.museumapi.model.MuseumObject
import com.squareup.picasso.Picasso

class HomeAdapter(private val list: List<MuseumObject>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(private val viewBinding: ItemHomeLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: MuseumObject) {
            item.let {
                viewBinding.tvTitle.text = it.title
                viewBinding.tvDetail.text = it.artistRole
                val imagePath = it.primaryImage
                if (imagePath.isNotEmpty())
                    Picasso.get().load(imagePath).into(viewBinding.ivPrincipal)
            }
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
        holder.onBind(list[position])
    }
}