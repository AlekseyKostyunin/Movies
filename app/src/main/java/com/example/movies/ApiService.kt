package com.example.movies

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&limit=15&rating.kp=7-10&sortField=votes.kp&sortType=-1&notNullFields=poster.url")
    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&limit=15&rating.kp=7-10&sortField=votes.kp&sortType=-1&notNullFields=poster.url")
    fun loadMovies(@Query("page") page: Int): Single<MovieResponse>

    // https://api.kinopoisk.dev/v1.4/movie/666?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE
    // https://api.kinopoisk.dev/v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&selectFields=id&id=666
    // https://api.kinopoisk.dev/v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE&id=666
    @GET("v1.4/movie?token=VRY7JS6-4TB4C8P-MR49NYR-PDYB8KE")
    fun loadTrailers(@Query("id") id: Int): Single<TrailerResponse>
}