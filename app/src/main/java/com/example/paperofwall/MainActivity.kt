package com.example.paperofwall

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paperofwall.adapters.WallpaperAdapter
import com.example.paperofwall.databinding.ActivityMainBinding
import com.example.paperofwall.domains.network.ApiClient.Companion.getWalls
import com.example.paperofwall.domains.network.ApiInterface
import com.example.paperofwall.models.HitsItem
import com.example.paperofwall.models.ResponseWallPaper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var wallList = mutableListOf<HitsItem?>()
    private lateinit var binding: ActivityMainBinding
    lateinit var wallpaperAdapter: WallpaperAdapter
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callWallpaperApi(search = "Virat Kohli",page = 1)
        wallpaperAdapter = WallpaperAdapter(wallList)
        binding.recyclerView.adapter = wallpaperAdapter


        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val childCount = layoutManager.childCount
                val totalCount = layoutManager.itemCount
                val firstCount = layoutManager.findFirstCompletelyVisibleItemPosition()

                if (childCount + firstCount >= totalCount) {
                    callWallpaperApi("Virat Kohli",page++)
                }
            }
        })
    }
    private fun callWallpaperApi(search: String, page: Int) {
        val wallpaperInterface = getWalls().create(ApiInterface::class.java)
        wallpaperInterface.getPapers(q = search, page = page)
            .enqueue(object : Callback<ResponseWallPaper> {
                override fun onResponse(
                    call: Call<ResponseWallPaper>,
                    response: Response<ResponseWallPaper>
                ) {
                    wallpaperAdapter.setData(response.body()!!.hits as MutableList)
                }

                override fun onFailure(call: Call<ResponseWallPaper>, t: Throwable) {

                }

            })
    }
}