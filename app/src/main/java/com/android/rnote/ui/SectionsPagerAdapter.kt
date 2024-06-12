package com.android.rnote.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.rnote.ui.music.MusicPlayerFragment
import com.android.rnote.ui.music.VideoFragment

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MusicPlayerFragment()
            1 -> fragment = VideoFragment()
        }
        return fragment as Fragment
    }

}