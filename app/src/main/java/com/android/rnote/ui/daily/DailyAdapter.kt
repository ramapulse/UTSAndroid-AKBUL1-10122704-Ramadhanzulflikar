package com.android.rnote.ui.daily

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.data.model.DailyActivity
import com.android.rnote.data.model.Gallery
import com.android.rnote.databinding.ItemDailyBinding
import com.android.rnote.databinding.ItemGalleryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DailyAdapter(private val context: Context, private val dailys: List<DailyActivity>) : RecyclerView.Adapter<DailyAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(dailys[position])
    }

    override fun getItemCount(): Int = dailys.size

    inner class NameViewHolder(private val binding: ItemDailyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: DailyActivity) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide.with(context).load(daily.imageUrl).placeholder(circularProgressDrawable).diskCacheStrategy(
                DiskCacheStrategy.DATA).override(200,200).into(binding.ivDaily)
            binding.tvDaily.text = daily.title
            binding.tvDailyDesc.text = daily.description
        }
    }
}