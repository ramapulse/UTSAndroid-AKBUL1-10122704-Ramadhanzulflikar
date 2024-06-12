package com.android.rnote.ui

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.android.rnote.R
import com.android.rnote.databinding.ItemWalkthroughBinding

data class WelcomePage(
    val title: String,
    val imageResId: Int,
    val desc: String
)

class WalkthroughAdapter(private val context: Context) : PagerAdapter() {

    private val welcomeData = listOf(
        WelcomePage(context.getString(R.string.welcome_title_one), R.drawable.img_welcome,context.getString(R.string.welcome_desc_one)),
        WelcomePage(context.getString(R.string.welcome_title_two), R.drawable.img_daily_friends,context.getString(R.string.welcome_desc_two)),
        WelcomePage(context.getString(R.string.welcome_title_three), R.drawable.img_music_video,context.getString(R.string.welcome_desc_three))
    )


    override fun getCount(): Int {
        return welcomeData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "${position+1}"
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = ItemWalkthroughBinding.inflate(inflater)
        val imageView = layout.ivWalk
        val pageData = welcomeData[position]
        imageView.setImageResource(pageData.imageResId)
        layout.tvTitleWalk.text = pageData.title
        layout.tvDescWalk.text = pageData.desc
        container.addView(layout.root)
        return layout.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}