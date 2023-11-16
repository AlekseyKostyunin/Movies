package com.example.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val TAG = "TEST_MainViewModel"
    }
    private var movies = MutableLiveData<List<Movie>>()
    private var compositeDisposable = CompositeDisposable()
    private var page = 1

    fun getMovies() : LiveData<List<Movie>>{
        return movies
    }

    fun loadMovies(){
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<MovieResponse> {
                override fun accept(t: MovieResponse) {
                    val loadedMovies = movies.value
                    if(loadedMovies != null){
//                        loadedMovies.containsAll(t.movies)
                        loadedMovies.plus(t.movies)
                        movies.value = loadedMovies
                    } else{
                        movies.value = t.movies
                    }
                    page++
                    Log.d(TAG,t.toString())
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    Log.d(TAG,"Error: " + t.message)
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}