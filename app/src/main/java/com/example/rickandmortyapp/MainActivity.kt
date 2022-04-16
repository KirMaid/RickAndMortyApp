package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {
    private val TAG = "MyApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainView = findViewById<RecyclerView>(R.id.main_list)
        val testName = findViewById<TextView>(R.id.test_name)
        //main_adapter =
//        val charactersURL = URL("https://rickandmortyapi.com/api/character/?page=1")
        val charactersURL = "https://rickandmortyapi.com/api/character/?page=1"
//        val connection = charactersURL.openConnection()
//        var responseBody = connection.getInputStream()
//        val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
        val moshiObject = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshiObject))
            .build()

        val rickAndMortyService:RetrofitService = retrofit.create(RetrofitService::class.java)

        rickAndMortyService.getCharacterById(1).enqueue(object:Callback<GetCharacterByIdResponse>{
            override fun onResponse(
                call: Call<GetCharacterByIdResponse>,
                response: Response<GetCharacterByIdResponse>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    testName.text = responseBody!!.name
                }
            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
//        if (connection.getResponseCode() == 200) {
//            // Success
//            // Further processing here
//        } else {
//            // Error handling code goes here
//        }
//        val queue = Volley.newRequestQueue(this)
//        val stringRequest = StringRequest(
//            Request.Method.GET, charactersURL,
//            Response.Listener { response ->
//                response.
//                // Display the first 500 characters of the response string.
//                testName.text = "Response is: ${response.substring(0, 500)}"
//            },
//            Response.ErrorListener { testName.text = "That didn't work!" })
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest)
    }

    fun testArray(){
        var cards = ArrayList<Character>()
        for (i in 1..20){
            //cards.add(Character("Test$i","Human$i","Male$i"))
        }
    }

    fun getOnePage(){

    }
}

