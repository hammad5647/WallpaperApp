package com.example.paperofwall.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paperofwall.R
import com.example.paperofwall.databinding.WallpaperSampleBinding
import com.example.paperofwall.models.HitsItem

class ViewPagerAdapter(private var list: MutableList<HitsItem?>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {
    class ViewPagerHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var binding = WallpaperSampleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_sample,parent,false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list[position]!!.largeImageURL)
            .placeholder(R.drawable.progress_view)
            .into(holder.binding.imageView)

    }
}