package com.example.paperofwall.domains.network

import com.example.paperofwall.models.ResponseWallPaper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface{
    @GET("api/")
    fun getPapers(
        @Query("key") key:String = "45820055-10bd5a456085bf3409c99e513",
        @Query("q") q:String,
        @Query("page") page:Int
    ) : Call<ResponseWallPaper>
}