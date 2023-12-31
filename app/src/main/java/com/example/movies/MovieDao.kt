package com.example.movies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable

@Dao
interface MovieDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies WHERE id == :movieId")
    fun getFavouriteMovie(movieId: Int): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie) : Completable
    @Query("DELETE FROM favourite_movies WHERE id == :movieId")
    fun removeMovie(movieId: Int) : Completable
}