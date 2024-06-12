package com.android.rnote.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.rnote.databinding.ActivityWalkthroughBinding

class WalkthroughActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalkthroughBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkthroughBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        initViewPager()
    }

    private fun initViewPager() {

        binding.walkTabLayout.setupWithViewPager(binding.walkViewPager)

        binding.walkViewPager.adapter = WalkthroughAdapter(this@WalkthroughActivity)
        binding.walkViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if(position == 2){
                    fadeIn(binding.btnNext)
                }else{
                    binding.btnNext.visibility = View.INVISIBLE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val intentToMain = Intent(this@WalkthroughActivity, MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }
    }

    fun fadeIn(view: View) {
        view.visibility = View.INVISIBLE
        ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f).apply {
            duration = 500
            start()
        }
        view.visibility = View.VISIBLE
    }

    fun fadeOut(view: View) {
        view.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f).apply {
            duration = 500
            start()
        }
        view.visibility = View.INVISIBLE
    }
}