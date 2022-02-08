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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookflow.app.BookApp
import com.example.bookflow.databinding.FragmentHomeBinding
import com.example.bookflow.ui.adapter.BooksListAdapter
import com.example.bookflow.viewmodel.BooksModelFactory
import com.example.bookflow.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


class HomeFragment : Fragment(), BooksListAdapter.ExtraerID {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var app : Application
    private lateinit var  recyclerView: RecyclerView
    private val adapter by lazy {  BooksListAdapter(this) }




    private val viewModel by activityViewModels<BooksViewModel> {
        BooksModelFactory((app as BookApp).repositorio)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = requireActivity().application

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.selectAllBooks.collect { books->
                    adapter.submitList(books)
                }
            }
        }

        return binding.root
    }

    override fun alHacerClick(id: Int) {

        viewModel.bookId.postValue(id)
        viewModel.insertBookDetail(id)
       
    }


}


/*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.selectAllBooks.collect { books->
                    adapter.submitList(books)
                }
            }
        }
                viewModel.listaBooksLD.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

 */

//        viewModel.booksListApi.observe(viewLifecycleOwner, {
//            binding.textView.text   = it[0].author
//        })
//            viewModel.listaApiLD.observe(viewLifecycleOwner, {
//                binding.textView.text   = it[0].author
//            })