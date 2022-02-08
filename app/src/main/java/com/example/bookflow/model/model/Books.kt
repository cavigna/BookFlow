package com.example.bookflow.model.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books_table")
data class Books(

    @SerializedName("id")
    @PrimaryKey
    var id: Int = 0,
    @SerializedName("author")
    var author: String = "",
    @SerializedName("country")
    var country: String = "",
    @SerializedName("imageLink")
    var imageLink: String = "",
    @SerializedName("language")
    var language: String = "",
    @SerializedName("title")
    var title: String = ""
)