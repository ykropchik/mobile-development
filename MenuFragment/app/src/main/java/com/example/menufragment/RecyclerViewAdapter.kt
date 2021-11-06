package com.example.menufragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val items: List<Pair<String, String>>, private val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img: ImageView? = null
        var name: TextView? = null

        init {
            img = itemView.findViewById(R.id.ivImage)
            name = itemView.findViewById(R.id.tvName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img?.let { Glide.with(this.context).load(items[position].first).into(it) }
//        holder.img?.setImageResource(items[position].first)
        holder.name?.text = items[position].second
    }

    override fun getItemCount(): Int {
        return items.size
    }
}