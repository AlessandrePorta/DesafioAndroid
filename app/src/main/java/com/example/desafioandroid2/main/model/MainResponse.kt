package com.example.desafioandroid2.main.model

data class MainResponse (val resources : MutableList<Resources> = gson.fromJson( Array<Resources>::class.java).toList())