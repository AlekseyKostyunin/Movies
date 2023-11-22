package com.example.movies

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private var viewModel = MovieDetailViewModel(application = Application())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]


        Glide.with(this)
            .load(intent.getStringExtra("poster"))
            .into(binding.imageViewPoster)
        binding.textViewTitle.text = intent.getStringExtra("name")
        binding.textViewYear.text = intent.getIntExtra("year", 666).toString()
        binding.textViewDescription.text = intent.getStringExtra("description")

        viewModel.loadTrailers(intent.getIntExtra("id", 666))
        viewModel.getTrailers().observe(this)   {
            Log.d("TEST_MovieDetailActivity",it.toString())
        }

    }

    companion object{
        fun newIntent(context: Context, movie: Movie): Intent{
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("poster", movie.poster.url)
            intent.putExtra("name", movie.name)
            intent.putExtra("year", movie.year)
            intent.putExtra("description", movie.description)
            intent.putExtra("id", movie.id)
            return intent
        }
    }
}