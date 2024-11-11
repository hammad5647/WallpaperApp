package com.example.paperofwall.activites

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.paperofwall.MainActivity
import com.example.paperofwall.MainActivity.Companion.wallList
import com.example.paperofwall.adapters.ViewPagerAdapter
import com.example.paperofwall.databinding.ActivityWallpaperBinding

class WallpaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWallpaperBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val position = intent.getIntExtra("image", 1)
        val url = intent.getStringExtra("url")

        val bitmap = url

        viewPagerAdapter = ViewPagerAdapter(MainActivity.wallList)
        binding.viewPager.adapter = viewPagerAdapter

        binding.viewPager.setCurrentItem(position, false)

        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.setWallpaperBtn.setOnClickListener {

            val wallPaperManager = WallpaperManager.getInstance(applicationContext)
            val currentItem = binding.viewPager.currentItem

            Glide.with(this).asBitmap().load(wallList[currentItem]!!.largeImageURL).listener(
                object: RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>,
                        isFirstResource: Boolean
                    ): Boolean {
                       return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        model: Any,
                        target: Target<Bitmap>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        wallPaperManager.setBitmap(resource)
                       return false
                    }

                }).submit()
            Toast.makeText(this, "Wallpaper Changed Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}