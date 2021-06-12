package com.dio.spotifyclonetostudie.presenter.model.data

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

 data class Categorie (

     @SerializedName("titulo") var title: String = "",
     @SerializedName("albuns") var albums: List<Albums> = arrayListOf()
)

data class Albums(
    @SerializedName("url_imagem") var imagem: String = ""
)

data class DataCategories(@SerializedName("categoria")

     val categories: List<Categorie>

)