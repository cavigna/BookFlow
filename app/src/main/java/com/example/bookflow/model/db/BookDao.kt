package com.example.bookflow.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookflow.model.model.BookDetail
import com.example.bookflow.model.model.Books
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertBooks(books: List<Books>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookDetail(book: BookDetail)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBookDetail(book: BookDetail)

    @Delete
    fun deleteBookDetail(book: BookDetail)

    @Query("SELECT * FROM book_details_table WHERE id=:id")
    fun selectBookDetail(id:Int): BookDetail

    @Query("SELECT * FROM books_table")
    fun selectAllBooks(): List<Books>

    @Query("SELECT * FROM books_table")
    fun selectAllBooksLD(): LiveData<List<Books>>

    @Query("SELECT * FROM books_table")
    fun selectAllBooksFlow():Flow<List<Books>>
}