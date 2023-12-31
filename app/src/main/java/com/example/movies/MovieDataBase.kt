package com.example.movies

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {

    companion object{
        private var db: MovieDataBase? = null
        private const val DB_NAME = "movie.db"
        private val LOCK = Any()

        fun getInstance(application: Application): MovieDataBase{
            synchronized(LOCK){
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    application,
                    MovieDataBase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun movieDao() : MovieDao

}