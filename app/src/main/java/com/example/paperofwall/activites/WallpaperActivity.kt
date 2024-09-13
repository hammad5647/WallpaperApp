package com.example.paperofwall.activites

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paperofwall.R
import com.example.paperofwall.databinding.ActivityWallpaperBinding

class WallpaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWallpaperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}