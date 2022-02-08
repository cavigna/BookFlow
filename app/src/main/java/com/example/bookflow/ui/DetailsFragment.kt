package com.example.bookflow.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.bookflow.app.BookApp
import com.example.bookflow.databinding.FragmentDetailsBinding
import com.example.bookflow.model.model.BookDetail
import com.example.bookflow.viewmodel.BooksModelFactory
import com.example.bookflow.viewmodel.BooksViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var app: Application

    private val viewModel by activityViewModels<BooksViewModel> { BooksModelFactory((app as BookApp).repositorio) }


    //private lateinit var app: Application

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = requireActivity().application


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        var currentBook = BookDetail()
        val idFromHome = viewModel.bookId.value!!


        lifecycleScope.launch(IO) {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.selectBookDetail(id = idFromHome)
                    .catch { e -> Log.i("error", e.message.toString()) }
                    .collect { book ->
                        currentBook = book
                        try {
                            with(binding) {
                                imageViewCover.load(book.imageLink)
                                tvTitulo.text = book.title
                            }
                        }catch (e: NullPointerException){}

                    }
            }
        }

        binding.button.setOnClickListener {

            when(currentBook.favorito){
                1-> currentBook.favorito =0
                0 -> currentBook.favorito = 1
            }
            viewModel.updateBookDetail(currentBook)
        }

        return binding.root
    }


}