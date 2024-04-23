package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

   lateinit var binding: ActivityMainBinding
   lateinit var retrofit : Retrofit
   lateinit var api: Yandex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Yandex::class.java)

        binding.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                doRequest()
            }
        })
    }

    fun doRequest() {
        api.complete(KEY,binding.editText.text.toString(),LANG,LIMIT).enqueue(object: Callback<Response>{
            override fun onResponse(p0: Call<Response>, p1: retrofit2.Response<Response>) {
                if(p1.isSuccessful) {
                    val response = p1.body()
                    binding.textView.text = response?.text?.joinToString("\n")
                }
            }

            override fun onFailure(p0: Call<Response>, p1: Throwable) {
                Log.d("RRR",p1.message.toString())
            }
        })
    }

    companion object {
        const val BASE_URL = "https://predictor.yandex.net"
        const val LANG = "en"
        const val KEY = "pdct.1.1.20231215T152437Z.359f4ba5ba7bcc9f.6e3254ffd7e39d6d6316461f5dd5b095a707420e"
        const val LIMIT = 5
    }
}