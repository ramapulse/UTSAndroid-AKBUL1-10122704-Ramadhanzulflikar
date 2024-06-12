package com.android.rnote.ui.music

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rnote.R
import com.android.rnote.databinding.FragmentHomeBinding
import com.android.rnote.databinding.FragmentVideoBinding


class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[MusicViewModel::class.java]

        viewModel.getAllVideoFavorites().observe(viewLifecycleOwner){ video ->
            if(video.isNotEmpty()){
                val currentVideo = video[0]
//                val adapter = VideoAdapter(requireContext(), video)
//                binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
//                binding.rvVideo.adapter = adapter

                binding.tvTitle.text = currentVideo.title
                val videoUri: Uri = Uri.parse("android.resource://com.android.rnote/" + currentVideo.fileUrl)
                binding.videoView.setVideoURI(videoUri)
                val mediaController = MediaController(context)
                mediaController.setAnchorView(binding.videoView)
                mediaController.setMediaPlayer(binding.videoView)
                binding.videoView.setMediaController(mediaController)
                binding.videoView.seekTo(1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}