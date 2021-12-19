package com.example.finalproject.ui.search
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.finalproject.R
import com.example.finalproject.data.Photo

class SearchViewAdapter(private val items: MutableList<Photo>, private val onClickListener: OnItemClickListener):
    RecyclerView.Adapter<SearchViewAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View, onClickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        var id: String? = null
        var image: ImageView? = null
        var downloadBtn: ImageButton? = null

        init {
            image = itemView.findViewById(R.id.ivPhoto)
//            downloadBtn = itemView.findViewById(R.id.ibDownloadBtn)
            itemView.setOnClickListener {
                id?.let { it1 -> onClickListener.onItemClick(it1) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String)
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
        holder.id = items[position].id
        holder.image?.let {
            Glide
                .with(holder.itemView.context)
                .load(items[position].photoSource.small)
                .override(SIZE_ORIGINAL)
                .into(it)
        }

//        if(downloadedPhotosIdList.indexOf(items[position].id) != -1) {
//            holder.downloadBtn?.setImageResource(R.drawable.ic_baseline_check_24)
//        } else {
//            holder.downloadBtn?.setImageResource(R.drawable.ic_baseline_save_alt_24)
//        }
//
//        holder.downloadBtn?.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(items[position].photoLinks.download))
//                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            holder.itemView.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}