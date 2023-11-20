package com.example.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

//    init {
//        loadMovies()
//
//    }
    companion object{
        const val TAG = "TEST_MainViewModel"
    }
    private var movies = MutableLiveData<List<Movie>>()
//    private var isLoading = MutableLiveData<Boolean>()
    private var compositeDisposable = CompositeDisposable()
    private var page = 1

    fun getMovies() : LiveData<List<Movie>>{
        return movies
    }

//    fun getIsLoading() : LiveData<Boolean>{
//        return isLoading
//    }

    fun loadMovies(){
//        val loading = isLoading.value
//        if((loading != null) && loading){
//            return
//        }
//        loading?.let { return }
        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { isLoading.value = true }
//            .doAfterTerminate { isLoading.value = false }
            .subscribe({ t ->
//                val loadedMovies = movies.value
//                if(loadedMovies != null){
//                    movies.value = movies.value?.plus(loadedMovies)
//                } else{
//                    movies.value = t.movies
//                }

                movies.value = t.movies

                Log.d(TAG,page.toString())
                page++
                Log.d(TAG,t.toString())
            }
            ) { t -> Log.d(TAG, "Error: " + t.message) }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}