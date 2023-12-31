package com.example.movies

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewModel = MainViewModel(application = Application())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewMovies = binding.recyclerViewMovies

        val moviesAdapter = MoviesAdapter(this)
        recyclerViewMovies.adapter = moviesAdapter
        recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getMovies().observe(this
        ) { moviesAdapter.movies  = it}
        viewModel.loadMovies()

//        viewModel.getIsLoading().observe(this) {
//            if (it) {
//                binding.progressBarLoading.visibility = View.VISIBLE
//            } else {
//                binding.progressBarLoading.visibility = View.GONE
//            }
//        }

//        moviesAdapter.onReachEndListener = object : MoviesAdapter.OnReachEndListener{
//            override fun onReachEnd() {
//                viewModel.loadMovies()
//            }
//        }
//
        moviesAdapter.onMovieClickListener = object : MoviesAdapter.OnMovieClickListener{
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@MainActivity, movie)
                startActivity(intent)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemFavourite){
            val intent = FavouriteMoviesActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }








}