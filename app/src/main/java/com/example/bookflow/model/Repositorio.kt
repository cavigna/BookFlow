package com.example.bookflow.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.bookflow.model.db.BookDao
import com.example.bookflow.model.model.BookDetail
import com.example.bookflow.model.model.Books
import com.example.bookflow.model.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repositorio(private val api: ApiService, private val dao: BookDao) {

    suspend fun fetchBooks() = api.fetchBooksListApi()
    suspend fun fetchBookDetail(id:Int) = api.fetchDetails(id)

    fun insertBooks(books: List<Books>) = dao.insertBooks(books)
    fun insertBookDetail(book: BookDetail) = dao.insertBookDetail(book)

    fun deleteBookDetail(book: BookDetail) = dao.deleteBookDetail(book)
    fun updateBookDetail(book: BookDetail) = dao.updateBookDetail(book)

    fun  selectBookDetail(id: Int) = flow {

        emit(dao.selectBookDetail(id))


    }

    val selectAllBooksAsLiveData: LiveData<List<Books>> = liveData {
        emit(dao.selectAllBooks())
    }

    val selectAllBooksAsFlow:Flow<List<Books>> = flow {
        emit(dao.selectAllBooks())
    }

    val selectAllBooksLD: LiveData<List<Books>> = dao.selectAllBooksLD()


    val selectAllBooksFlow = dao.selectAllBooksFlow()

}

/*

    val fetchBooks : Flow<List<Books>> = flow {
        while (true){
            val fetchBooks = api.fetchBooksListApi()
            emit(fetchBooks)
        }
    }


    val selectAllBooks = flow {
        while (true){
            val listadoLibros = dao.selectAllBooks()
            emit(listadoLibros)
        }
    }
 */