package com.example.bookflow.model.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "book_details_table")
data class BookDetail(

    @SerializedName("id")
    @PrimaryKey
    var id: Int = 0,
    @SerializedName("author")
    var author: String = "",
    @SerializedName("country")
    var country: String = "",
    @SerializedName("delivery")
    var delivery: Boolean = false,
    @SerializedName("imageLink")
    var imageLink: String = "",
    @SerializedName("language")
    var language: String = "",
    @SerializedName("lastPrice")
    var lastPrice: Int = 0,
    @SerializedName("link")
    var link: String = "",
    @SerializedName("pages")
    var pages: Int = 0,
    @SerializedName("price")
    var price: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("year")
    var year: Int = 0,

    var favorito: Int = 0
)