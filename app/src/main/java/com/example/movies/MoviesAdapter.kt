package com.example.movies

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieItemBinding

class MoviesAdapter(private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    var onReachEndListener : OnReachEndListener? = null

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var binding : MovieItemBinding = MovieItemBinding.bind(itemView)
        var imageViewPoster = binding.imageViewPoster
        var textViewRating = binding.textViewRating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("TEST_Position",position.toString())
        val movie = movies[position]
        Glide.with(holder.itemView)
            .load(movie.poster.url)
            .into(holder.imageViewPoster)

        val rating : Double = movie.rating.kp
        val backgroundId = if(rating >= 7.0){
            R.drawable.circle_green
        } else if(rating > 5.0){
            R.drawable.circle_orange
        } else {
            R.drawable.circle_red
        }

        holder.textViewRating.text = rating.toString().substring(0, 3)
        holder.textViewRating.setBackgroundResource(backgroundId)

        if (onReachEndListener != null && position == movies.size - 1){
             onReachEndListener?.onReachEnd()
        }
    }

    interface OnReachEndListener{
        fun onReachEnd()
    }
}

