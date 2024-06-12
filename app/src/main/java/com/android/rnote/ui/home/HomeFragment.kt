package com.android.rnote.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.R
import com.android.rnote.databinding.FragmentGalleryBinding
import com.android.rnote.databinding.FragmentHomeBinding
import com.android.rnote.ui.gallery.GalleryAdapter
import com.android.rnote.ui.gallery.GalleryViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        viewModel.getProfileById().observe(viewLifecycleOwner){ profile ->
            if(profile?.name != null){
                val circularProgressDrawable = CircularProgressDrawable(requireContext())
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()
                Glide.with(requireContext()).load(profile.photoUrl).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(500,500).into(binding.imageViewCircleWithStroke)
                Glide.with(requireContext()).load(profile.photoUrl).placeholder(circularProgressDrawable).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivProfile)
                binding.tvName.text = profile.name
                binding.tvDesc.text = profile.description
            }
        }

        viewModel.getInterestsByProfileId().observe(viewLifecycleOwner){ interest ->
            Log.e("checkpoint a", interest.toString())
            if(interest.isNotEmpty()){
                val currentInterest = interest[0]
                binding.tvHobby.text = currentInterest.hobby
                binding.tvInterest.text = currentInterest.interest
                binding.tvCitaCita.text = currentInterest.goal

                val circularProgressDrawable = CircularProgressDrawable(requireContext())
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.backgroundColor = R.color.white
                circularProgressDrawable.start()

                val gson = Gson()
                val type = object : com.google.gson.reflect.TypeToken<List<Pair<String, String>>>() {}.type
                val listMakesFromJson: List<Pair<String, String>> = gson.fromJson(currentInterest.makes, type)

                val listMikesFromJson: List<Pair<String, String>> = gson.fromJson(currentInterest.mikes, type)

                binding.tvMakes1.text = listMakesFromJson[0].second
                binding.tvMakes2.text = listMakesFromJson[1].second
                binding.tvMakes3.text = listMakesFromJson[2].second
                binding.tvMikes1.text = listMikesFromJson[0].second
                binding.tvMikes2.text = listMikesFromJson[1].second

                Glide.with(requireContext()).load(listMakesFromJson[0].first).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivMakes1)
                Glide.with(requireContext()).load(listMakesFromJson[1].first).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivMakes2)
                Glide.with(requireContext()).load(listMakesFromJson[2].first).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivMakes3)
                Glide.with(requireContext()).load(listMikesFromJson[0].first).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivMikes1)
                Glide.with(requireContext()).load(listMikesFromJson[1].first).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivMikes2)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}