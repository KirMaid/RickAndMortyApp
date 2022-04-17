package com.example.rickandmortyapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {
    private var dataSet: ArrayList<GetCharacterByIdResponse> = arrayListOf()
    private val viewModel:MyViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    private val TAG = "MyApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainView = findViewById<RecyclerView>(R.id.main_list)

        mainView.layoutManager = LinearLayoutManager(this)
        var myAdapter = ListAdapter(dataSet)
        mainView.adapter = myAdapter

        for (i in 1..10){
            viewModel.updateCharacter(i)
        }
        //viewModel.updateCharacter(8)
        viewModel.characterByIdLiveData.observe(this){response->
            if (response==null){
                Toast.makeText(this,"Ooops, date not found",Toast.LENGTH_LONG).show()
                return@observe
            }
            dataSet.add(response)
            myAdapter = ListAdapter(dataSet)
            mainView.adapter = myAdapter
//            Toast.makeText(this,response.name,Toast.LENGTH_LONG).show()
        }

        //getOnePage();

//        val moshiObject = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://rickandmortyapi.com/api/")
//            .addConverterFactory(MoshiConverterFactory.create(moshiObject))
//            .build()
//
//        val rickAndMortyService:RetrofitService = retrofit.create(RetrofitService::class.java)
//
//        rickAndMortyService.getCharacterById(1).enqueue(object:Callback<GetCharacterByIdResponse>{
//            override fun onResponse(
//                call: Call<GetCharacterByIdResponse>,
//                response: Response<GetCharacterByIdResponse>
//            ) {
//                if(response.isSuccessful){
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        dataSet.add(responseBody)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })


        //val myAdapter = ListAdapter(dataSet)
        //mainView.adapter = myAdapter


    }


    private suspend fun getOnePage(){

        for (i in 1..10){
            RetrofitClient.apiClient.getCharacterById(i)
//            rickAndMortyService.getCharacterById(i).enqueue(object:Callback<GetCharacterByIdResponse>{
//                override fun onResponse(
//                    call: Call<GetCharacterByIdResponse>,
//                    response: Response<GetCharacterByIdResponse>
//                ) {
//                    if(response.isSuccessful){
//                        val responseBody = response.body()!!
//                        dataSet.add(responseBody)
//                    }
//                }
//
//                override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
        }
    }
}

