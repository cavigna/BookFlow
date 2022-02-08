package com.example.bookflow.model.network

import com.example.bookflow.model.model.BookDetail
import com.example.bookflow.model.model.Books
import retrofit2.http.GET
import retrofit2.http.Path


//https://my-json-server.typicode.com/Himuravidal/anchorBooks/books
interface ApiService {
    @GET("books")
    suspend fun fetchBooksListApi(): List<Books>

    @GET("bookDetail/{id}")
    suspend fun fetchDetails(
        @Path(value = "id") id: Int
    ) :BookDetail
}