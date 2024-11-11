package com.example.paperofwall

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isNotEmpty
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
    companion object {
        var wallList = mutableListOf<HitsItem?>()
    }

    private lateinit var binding: ActivityMainBinding
    lateinit var wallpaperAdapter: WallpaperAdapter
    var page = 1
    var search = "cars"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callWallpaperApi(search, page)

        wallpaperAdapter = WallpaperAdapter(wallList)
        binding.recyclerView.adapter = wallpaperAdapter
        searchView()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val childCount = layoutManager.childCount
                val totalCount = layoutManager.itemCount
                val firstCount = layoutManager.findFirstCompletelyVisibleItemPosition()

                if (childCount + firstCount >= totalCount) {
                    callWallpaperApi(search, page++)
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

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search = query!!
                wallpaperAdapter.clearList()
                callWallpaperApi(search = query, page)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}