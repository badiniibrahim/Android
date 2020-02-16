package com.badini.movie.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.xml.sax.DTDHandler

@Entity
data class Favoris(
    val id: Int = 0,
    val title: String = "",
    val imgurl: String = "",
    val releasedate: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var favorisid : Int = 0
}