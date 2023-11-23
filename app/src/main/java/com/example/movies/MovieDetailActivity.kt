package com.example.movies

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityMovieDetailBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private var viewModel = MovieDetailViewModel(application = Application())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewTrailers = binding.recyclerViewTrailers
        val trailersAdapter = TrailersAdapter(this)
        recyclerViewTrailers.adapter = trailersAdapter

//        recyclerViewTrailers.layoutManager = GridLayoutManager(this,
//            1)


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
            trailersAdapter.trailers = it
        }
        trailersAdapter.onTrailerClickListener = object : TrailersAdapter.OnTrailerClickListener {
            override fun onTrailerClick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        }
        viewModel.loadReview(intent.getIntExtra("id", 666))
        viewModel.getReviews().observe(this){
            Log.d("TEST_загрузка_отзывов", "Ok: " + it.toString())
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