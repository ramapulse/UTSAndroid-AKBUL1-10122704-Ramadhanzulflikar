package com.android.rnote.ui.daily

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.rnote.R
import com.android.rnote.data.model.DailyActivity
import com.android.rnote.data.model.Friend
import com.android.rnote.databinding.ItemDailyBinding
import com.android.rnote.databinding.ItemFriendsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FriendAdapter(private val context: Context, private val friends: List<Friend>) : RecyclerView.Adapter<FriendAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    override fun getItemCount(): Int = friends.size

    inner class NameViewHolder(private val binding: ItemFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setTint(context.getColor(R.color.white))
            circularProgressDrawable.backgroundColor = R.color.white
            circularProgressDrawable.start()
            Glide.with(context).load(friend.imageUrl).placeholder(circularProgressDrawable).diskCacheStrategy(
                DiskCacheStrategy.DATA).override(200,200).into(binding.ivFriend)
            binding.tvFriend.text = friend.name
        }
    }
}