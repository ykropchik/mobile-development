package com.example.recyclerviewtask

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val items: List<Triple<Int, String, String>>):
    RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var name: TextView? = null
        var number: TextView? = null

        init {
            img = itemView.findViewById(R.id.ivImage)
            name = itemView.findViewById(R.id.tvName)
            number = itemView.findViewById(R.id.tvNumber)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img?.setImageResource(items[position].first)
        holder.name?.text = items[position].second
        holder.number?.text = items[position].third
    }

    override fun getItemCount(): Int {
        return items.size
    }
}