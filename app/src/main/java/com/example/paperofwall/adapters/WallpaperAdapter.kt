package com.example.paperofwall.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paperofwall.R
import com.example.paperofwall.activites.WallpaperActivity
import com.example.paperofwall.databinding.HomeSampleBinding
import com.example.paperofwall.models.HitsItem

class WallpaperAdapter(private var wallist: MutableList<HitsItem?>) :
    RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {
    class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = HomeSampleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_sample, parent, false)

        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallist.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(wallist[position]!!.previewURL)
            .placeholder(R.drawable.progress_view)
            .into(holder.binding.imageView)

        holder.binding.wallpaperSample.setOnClickListener{
            val intent = Intent(holder.itemView.context,WallpaperActivity::class.java)
            intent.putExtra("image",position)
            intent.putExtra("url",wallist[position]!!.largeImageURL)
            holder.itemView.context.startActivity(intent)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<HitsItem?>) {
        wallist.addAll(list)
        notifyDataSetChanged()
    }
    fun clearList(){
        wallist.clear()
    }
}