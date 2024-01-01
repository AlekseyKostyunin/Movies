package com.example.movies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityMovieDetailBinding
import java.io.Serializable

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewTrailers = binding.recyclerViewTrailers
        val recyclerViewReviews = binding.recyclerViewReviews

        val trailersAdapter = TrailersAdapter(this)
        recyclerViewTrailers.adapter = trailersAdapter

        val reviewsAdapter = ReviewsAdapter(this)
        recyclerViewReviews.adapter = reviewsAdapter

        val viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                this.getSerializableExtra(key, m_class)!!
            else
                this.getSerializableExtra(key) as T
        }

        val movie = intent.getSerializable("movie", Movie::class.java)

        Glide.with(this)
            .load(movie?.poster?.url)
            .into(binding.imageViewPoster)
        binding.textViewTitle.text = movie?.name
        binding.textViewYear.text = movie?.year.toString()
        binding.textViewDescription.text = movie?.description

        movie?.id?.let { viewModel.loadTrailers(it) }
        viewModel.getTrailers().observe(this)   {
            Log.d("TEST_MovieDetailActivity",it.toString())
            trailersAdapter.trailers = it
        }
        trailersAdapter.onTrailerClickListener = object : TrailersAdapter.OnTrailerClickListener {
            override fun onTrailerClick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        }
//        viewModel.loadReviews(intent.getIntExtra("id", 666))
        movie?.id?.let { viewModel.loadReviews(it) }
        viewModel.getReviews().observe(this){
            reviewsAdapter.reviews = it
            //Log.d("TEST_загрузка_отзывов", "Ok: " + it.toString())
        }

        val starOff = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_off
        )
        val starOn = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_on
        )

        movie?.id?.let {
            viewModel.getFavouriteMovie(it)
                .observe(this){
                    if(it == null){
                        binding.imageViewStar.setImageDrawable(starOff)
                        binding.imageViewStar.setOnClickListener{
                            viewModel.insertMovie(movie)
                        }
                    }else{
                        binding.imageViewStar.setImageDrawable(starOn)
                        binding.imageViewStar.setOnClickListener{
                            viewModel.removeMovie(movie.id)
                        }
                    }
                }
        }

    }

    companion object{
//        fun newIntent(context: Context, movie: Movie): Intent {
//            val intent = Intent(context, MovieDetailActivity::class.java)
//            intent.putExtra("poster", movie.poster.url)
//            intent.putExtra("name", movie.name)
//            intent.putExtra("year", movie.year)
//            intent.putExtra("description", movie.description)
//            intent.putExtra("rating", movie.rating.kp)
//            intent.putExtra("id", movie.id)
//            return intent
//        }

        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            return intent
        }
    }

}