package com.example.myapplication

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Response (
    var endOfWord : Boolean = false,
    var pos : Int = 0,
    var text : ArrayList<String> = arrayListOf()
)