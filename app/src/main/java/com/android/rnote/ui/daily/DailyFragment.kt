package com.android.rnote.ui.daily

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.android.rnote.R
import com.android.rnote.databinding.FragmentDailyBinding
import com.android.rnote.databinding.FragmentProfileBinding
import com.android.rnote.ui.gallery.GalleryAdapter
import com.android.rnote.ui.profile.ProfileViewModel


class DailyFragment : Fragment() {

    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[DailyViewModel::class.java]

        viewModel.getAllDailyActivities().observe(viewLifecycleOwner){ daily ->
            if(daily.isNotEmpty()){
                val adapter = DailyAdapter(requireContext(), daily)
                binding.rvDaily.layoutManager = LinearLayoutManager(requireContext())
                binding.rvDaily.isNestedScrollingEnabled = false
                binding.rvDaily.adapter = adapter
            }
        }

        viewModel.getAllFriends().observe(viewLifecycleOwner){ friend ->
            if(friend.isNotEmpty()){
                val adapter = FriendAdapter(requireContext(), friend)
                binding.rvFriends.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
                binding.rvFriends.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}