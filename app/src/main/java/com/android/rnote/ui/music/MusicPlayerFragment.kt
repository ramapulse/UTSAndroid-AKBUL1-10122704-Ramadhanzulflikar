package com.android.rnote.ui.music

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rnote.R
import com.android.rnote.databinding.FragmentMusicPlayerBinding
import com.android.rnote.ui.daily.DailyAdapter
import java.io.IOException


class MusicPlayerFragment : Fragment() {

    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = ViewModelProvider(requireActivity())[MusicViewModel::class.java]

        var mediaPlayer: MediaPlayer? = null


        viewModel.getAllMusicFavorites().observe(viewLifecycleOwner){ music ->
            if(music.isNotEmpty()){
                val adapter = MusicPlayerAdapter(requireContext(), music, { link, title  ->
                    mediaPlayer?.stop()
                    mediaPlayer?.release()

                    mediaPlayer = MediaPlayer.create(requireContext(), link)
                    mediaPlayer?.start()

                    Toast.makeText(requireContext(), "Playing $title", Toast.LENGTH_SHORT).show()

                    mediaPlayer?.setOnCompletionListener {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                },{
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = null
                })
                binding.rvMusicPlayer.layoutManager = LinearLayoutManager(requireContext())
                binding.rvMusicPlayer.adapter = adapter
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}