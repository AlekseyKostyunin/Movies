package com.example.movies

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.databinding.TrailerItemBinding

class TrailersAdapter(context: Context) : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>(){

     var onTrailerClickListener : OnTrailerClickListener? = null
     var trailers: List<Trailer> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class TrailersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var binding : TrailerItemBinding = TrailerItemBinding.bind(itemView)
        var textTrailerName = binding.textTrailerName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.trailer_item,
                parent,
                false
            )
        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        Log.d("TEST_Position_T",position.toString())
        val trailer = trailers[position]
        holder.textTrailerName.text = trailer.name

        holder.itemView.setOnClickListener{
//            onTrailerClickListener?.onTrailerClick(trailer)
            if(onTrailerClickListener != null){
                onTrailerClickListener?.onTrailerClick(trailer)
            }
        }
    }

    interface OnTrailerClickListener{
        fun onTrailerClick(trailer: Trailer)
    }
}