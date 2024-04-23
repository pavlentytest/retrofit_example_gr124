package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("endOfWord") var endOfWord : Boolean? = null,
    @SerializedName("pos") var pos : Int? = null,
    @SerializedName("text") var text : ArrayList<String> = arrayListOf()
)