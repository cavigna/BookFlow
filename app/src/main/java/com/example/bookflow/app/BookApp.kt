package com.example.bookflow.app

import android.app.Application
import com.example.bookflow.model.db.BaseDeDatos
import com.example.bookflow.model.Repositorio
import com.example.bookflow.model.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookApp: Application() {


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Himuravidal/anchorBooks/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    val database by lazy { BaseDeDatos.getDataBase(this) }

     val repositorio by lazy { Repositorio(retrofit, database.dao()) }




}