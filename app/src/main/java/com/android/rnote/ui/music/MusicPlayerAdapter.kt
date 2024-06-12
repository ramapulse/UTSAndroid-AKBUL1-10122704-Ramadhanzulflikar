package com.android.rnote.ui.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.data.model.DailyActivity
import com.android.rnote.data.model.MusicFavorite
import com.android.rnote.databinding.ItemDailyBinding
import com.android.rnote.databinding.ItemMusicBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MusicPlayerAdapter(private val context: Context, private val musics: List<MusicFavorite>, private val  playCallback : (Int, String) -> Unit, private val  pauseCallback : () -> Unit) : RecyclerView.Adapter<MusicPlayerAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(musics[position])
    }

    override fun getItemCount(): Int = musics.size

    inner class NameViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(music: MusicFavorite) {
            binding.tvDaily.text = music.title
            binding.tvDailyDesc.text = music.artist
            binding.btnPlay.setOnClickListener {
                playCallback.invoke(music.fileUrl, music.title)
            }

            binding.btnStop.setOnClickListener {
                pauseCallback.invoke()
            }
        }
    }
}