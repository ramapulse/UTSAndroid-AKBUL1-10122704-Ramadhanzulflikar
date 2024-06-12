package com.android.rnote.ui.gallery

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.R
import com.android.rnote.databinding.DialogGalleryZoomBinding
import com.android.rnote.databinding.FragmentGalleryBinding
import com.android.rnote.ui.FullWithDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            val gallery = viewModel.getAllGalleryItems()
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
                val adapter = GalleryAdapter(requireContext(), gallery) { openedLink ->
                    showPhotoZoom(openedLink)
                }
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 3)
                binding.rvGallery.adapter = adapter
            }
        }
    }

    private fun showPhotoZoom(link: String){
        val mAuthAlertDialogView = DialogGalleryZoomBinding.inflate(layoutInflater)
        val mAuthAlertDialog =  FullWithDialog(requireContext(),R.style.FullscreenDialog)
        mAuthAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mAuthAlertDialog.setCancelable(false)
        mAuthAlertDialog.setContentView(mAuthAlertDialogView.root)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(mAuthAlertDialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.dimAmount = 0.3f
        mAuthAlertDialog.show()
        mAuthAlertDialog.window?.attributes = lp
        val window = mAuthAlertDialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mAuthAlertDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        mAuthAlertDialogView.ivClose.setOnClickListener {
            mAuthAlertDialog.dismiss()
        }

        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.backgroundColor = R.color.white
        circularProgressDrawable.start()
        Glide.with(this@GalleryFragment).load(link).diskCacheStrategy(DiskCacheStrategy.DATA).into(mAuthAlertDialogView.ivImage)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}