package com.android.rnote.ui.music

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.android.rnote.data.model.MusicFavorite
import com.android.rnote.data.model.VideoFavorite
import com.android.rnote.databinding.ItemMusicBinding
import com.android.rnote.databinding.ItemVideoBinding


class VideoAdapter(private val context: Context, private val videos: List<VideoFavorite>) : RecyclerView.Adapter<VideoAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size

    inner class NameViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoFavorite) {
            binding.tvTitle.text = video.title
            val videoUri: Uri = Uri.parse("android.resource://com.android.rnote/")

            binding.videoView.setVideoURI(videoUri)
            val mediaController = MediaController(context)
            mediaController.setAnchorView(binding.videoView)
            mediaController.setMediaPlayer(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.seekTo(1)
        }
    }
}