package com.example.desafioandroid2.main.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Resources(

    @SerializedName("value") val value: String,
    @SerializedName("resource_id") val resource_id: String,
    @SerializedName("updated_at") val updated_at : Date

)
