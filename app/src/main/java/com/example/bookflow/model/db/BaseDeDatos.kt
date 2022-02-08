package com.example.bookflow.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookflow.model.model.BookDetail
import com.example.bookflow.model.model.Books

@Database(entities = [Books::class, BookDetail::class], version = 1, exportSchema = false)
abstract class BaseDeDatos : RoomDatabase() {
    abstract fun dao() : BookDao

    companion object {

        @Volatile
        private var INSTANCE: BaseDeDatos? = null

        fun getDataBase(context: Context): BaseDeDatos {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "books_db"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}