package com.android.rnote.ui.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.BuildConfig
import com.android.rnote.R
import com.android.rnote.databinding.DialogAboutAppBinding
import com.android.rnote.databinding.FragmentProfileBinding
import com.android.rnote.ui.FullWithDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        viewModel.getProfileById().observe(viewLifecycleOwner){ profile ->
            if(profile?.name != null){
                val circularProgressDrawable = CircularProgressDrawable(requireContext())
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()
                Glide.with(requireContext()).load(profile.photoUrl).placeholder(circularProgressDrawable).diskCacheStrategy(
                    DiskCacheStrategy.DATA).override(200,200).placeholder(circularProgressDrawable).into(binding.ivProfile)
                binding.tvName.text = profile.name
                binding.tvDesc.text = profile.description
                binding.tvPhone.text = profile.phoneNumber
                binding.tvEmail.text = profile.email
                binding.btnMap.setOnClickListener {
                    openGmapsIntent(profile.latitude.toString(), profile.longitude.toString())
                }
                binding.btnInsta.setOnClickListener {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(profile.socialMediaLink))
                    startActivity(browserIntent)
                }

                binding.btnAbout.setOnClickListener {
                    showAboutApp()
                }
            }
        }
    }

    private fun showAboutApp(){
        val mAuthAlertDialogView = DialogAboutAppBinding.inflate(layoutInflater)
        val mAuthAlertDialog =  FullWithDialog(requireContext(), R.style.FullscreenDialog)
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
        mAuthAlertDialogView.tvVersion.text="Version: code(${BuildConfig.VERSION_CODE}) name(${BuildConfig.VERSION_NAME})"
        mAuthAlertDialogView.ivClose.setOnClickListener {
            mAuthAlertDialog.dismiss()
        }

    }

    private fun openGmapsIntent(lat: String, lon: String) {
        val mapIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:<$lat>,<$lon>?q=<$lat>,<$lon>(Lokasi Saya)")
        )
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}