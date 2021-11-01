package com.example.desafioandroid2.main.model

import com.google.gson.annotations.SerializedName

data class MainResponse (

    @SerializedName("resource") val resources : List<Resources>

)