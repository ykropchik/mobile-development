package com.example.finalproject.ui.savedPhotos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import java.io.File
import java.net.URI

class SavedPhotosViewAdapter(private val items: ArrayList<URI>, private val onClickListener: OnItemClickListener):
    RecyclerView.Adapter<SavedPhotosViewAdapter.CustomViewHolder>() {


    class CustomViewHolder(itemView: View, onClickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        var uri: String? = null
        var image: ImageView? = null

        init {
            image = itemView.findViewById(R.id.ivPhoto)
            itemView.setOnClickListener {
                uri?.let { it1 -> onClickListener.onItemClick(it1) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(uri: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.images_list_element, parent, false)
        return CustomViewHolder(itemView, onClickListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.uri = items[position].path
        holder.image?.let {
            Glide
                .with(holder.itemView.context)
                .load(
                    File(items[position].path)
                )
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}