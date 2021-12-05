package com.example.networktask

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import kotlin.coroutines.coroutineContext


class RecyclerViewAdapter(private val context: Context, private val items: List<Repo>):
    RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var date: TextView? = null

        init {
            name = itemView.findViewById(R.id.tvName)
            date = itemView.findViewById(R.id.tvDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name?.text = items[position].name
        holder.date?.text = "Дата создания: " + items[position].creationDate?.replace('T', ' ')
            ?.removeSuffix("Z")
            ?.toDate()
            ?.formatTo("dd MMM yyyy HH:mm").toString()

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(items[position].url))
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

}