package com.example.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        loadMovies()
    }
    companion object{
        const val TAG = "TEST_MainViewModel"
    }
    private var movies = MutableLiveData<List<Movie>>()
    private var isLoading = MutableLiveData<Boolean>(false)
    private var compositeDisposable = CompositeDisposable()
    private var page = 1

    fun getMovies() : LiveData<List<Movie>>{
        return movies
    }

    fun getIsLoading() : LiveData<Boolean>{
        return isLoading
    }

    fun loadMovies(){
        val loading = isLoading.value
        if((loading != null) && loading){
            return
        }
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(object : Consumer<Disposable>{
                override fun accept(t: Disposable) {
                    isLoading.value = true
                }
            })
            .doAfterTerminate(object : Action{
                override fun run() {
                    isLoading.value = false
                }
            })
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
                    Log.d(TAG,page.toString())
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