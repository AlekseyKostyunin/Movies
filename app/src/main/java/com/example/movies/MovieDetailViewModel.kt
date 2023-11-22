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

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val TAG = "TEST_MovieDetailViewModel"

    }

    private var compositeDisposable = CompositeDisposable()
    private var trailers = MutableLiveData<List<Trailer>>()

    fun getTrailers() : LiveData<List<Trailer>> {
        return trailers
    }

    fun loadTrailers(id : Int){
        val disposable = ApiFactory.apiService.loadTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<TrailerResponse> {
                override fun accept(t: TrailerResponse) {
                    trailers.value = t.videosList[0].trailersList.trailers
                    Log.d(TAG, "Ok: " + t.toString())
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    Log.d(TAG, t.toString())
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}