package com.example.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.ActivityFavouriteMoviesBinding
import com.example.movies.databinding.ActivityMovieDetailBinding

class FavouriteMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        binding = ActivityFavouriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewMovie = binding.recyclerViewMovies
        val moviesAdapter = MoviesAdapter(this)
        moviesAdapter.onMovieClickListener = object : MoviesAdapter.OnMovieClickListener{
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@FavouriteMoviesActivity, movie)
                startActivity(intent)
            }
        }

        recyclerViewMovie.layoutManager = GridLayoutManager(this,2)
        recyclerViewMovie.adapter = moviesAdapter

        val viewModel = ViewModelProvider(this)[FavouriteMoviesViewModel::class.java]

        viewModel.getMovies().observe(this){
            moviesAdapter.movies = it
        }
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context,FavouriteMoviesActivity::class.java)
        }
    }

}