package com.example.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.movies.databinding.ActivityMovieDetailBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(intent.getStringExtra("poster"))
            .into(binding.imageViewPoster)
        binding.textViewTitle.text = intent.getStringExtra("name")
        binding.textViewYear.text = intent.getIntExtra("year", 666).toString()
        binding.textViewDescription.text = intent.getStringExtra("description")

        var result = ApiFactory.apiService.loadTrailers(intent.getIntExtra("id", 666))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<TrailerResponse>{
                override fun accept(t: TrailerResponse) {
                    Log.d("TEST_MovieDetailActivity", "Ok: " + t.toString())
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    Log.d("TEST_MovieDetailActivity", "Error: " + t.message)
                }
            })

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