package com.android.rnote.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.data.model.Gallery
import com.android.rnote.databinding.ItemGalleryBinding
import com.android.rnote.ui.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import java.util.Objects


class GalleryAdapter(private val context: Context, private val gallerys: List<Gallery>, private val  chooseCallback : (String) -> Unit) : RecyclerView.Adapter<GalleryAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(gallerys[position])
    }

    override fun getItemCount(): Int = gallerys.size

    inner class NameViewHolder(private val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gallery: Gallery) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            binding.ivGallery.setOnClickListener {
                chooseCallback.invoke(gallery.imageUrl)
            }
            Glide.with(context).load(gallery.imageUrl).placeholder(circularProgressDrawable).diskCacheStrategy(DiskCacheStrategy.DATA).override(500,500).into(binding.ivGallery)
        }
    }
}
