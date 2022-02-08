package com.example.bookflow.viewmodel

import androidx.lifecycle.*
import com.example.bookflow.model.model.Books
import com.example.bookflow.model.Repositorio
import com.example.bookflow.model.model.BookDetail
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class BooksViewModel(private val repo: Repositorio) : ViewModel() {
    val listaBooksLD: LiveData<List<Books>> = repo.selectAllBooksLD
    val listaBooksFlow : Flow<List<Books>> = repo.selectAllBooksFlow

    val bookId = MutableLiveData(1)


    val mutablePrueba = MutableLiveData<List<Books>>()

    init {
        viewModelScope.launch(IO) {
            val listadoApi = repo.fetchBooks()
            repo.insertBooks(listadoApi)
            bookId.postValue(1)


            repo.selectAllBooksFlow.collect { books ->
                mutablePrueba.postValue(books)
            }
        }


    }

    fun deleteBookDetail(book:BookDetail) = repo.deleteBookDetail(book)
    fun updateBookDetail(book: BookDetail) {
        viewModelScope.launch(IO) { repo.updateBookDetail(book) }
    }

    fun insertBookDetail(id:Int){
        viewModelScope.launch(IO) {
            val book = repo.fetchBookDetail(id)
            book.let { repo.insertBookDetail(it) }


        }
    }

    //val selectBookDetail = bookId.value?.let { repo.selectBookDetail(it) }
    fun  selectBookDetail(id: Int) = repo.selectBookDetail(id)
    fun  selectBookDetail2(id: Int) = viewModelScope.launch(IO) {
        repo.selectBookDetail(id)
    }


    //val selectAllBooks = repo.selectAllBooks
    val selectAllBooks = repo.selectAllBooksFlow


}


class BooksModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksViewModel(repositorio) as T
    }

}

/*

val listaBooks: LiveData<List<Books>> get() = _listaBooks
    private var _listaBooks = MutableLiveData<List<Books>>()
    val booksListApi = MutableLiveData<List<Books>>()
    val listaApiLD = liveData {
        emit(repo.fetchBooks())

    }
 */