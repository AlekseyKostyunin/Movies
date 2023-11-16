package com.example.movies

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&limit=10&rating.kp=7-10&sortField=votes.kp&sortType=-1&notNullFields=poster.url")
    fun loadMovies(@Query("page") page: Int): Single<MovieResponse>
}