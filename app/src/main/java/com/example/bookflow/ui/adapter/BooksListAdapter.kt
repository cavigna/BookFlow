package com.example.bookflow.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookflow.R
import com.example.bookflow.databinding.ItemRowBinding
import com.example.bookflow.model.model.Books

class BooksListAdapter(private val extraerID: ExtraerID) : ListAdapter<Books, MyViewHolder>(Comparador()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = getItem(position)

        with(holder.binding) {
            imageViewLogoRow.load(book.imageLink)
            tvTituloRow.text = book.title
            tvAuthorRow.text = book.author
            tvLanguageRow.text = book.language

            cardView.setOnClickListener {
                extraerID.alHacerClick(id = book.id)
                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        }

    }

    interface ExtraerID {
        fun alHacerClick(id: Int)
    }

}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding.root)
        }
    }

}

class Comparador : DiffUtil.ItemCallback<Books>() {
    override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
        return oldItem.id == newItem.id
    }

}
