package com.example.rickandmortyapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import retrofit2.*
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {
    private var dataSet: ArrayList<GetCharacterByIdResponse> = arrayListOf()
    private var dataSetTest: ArrayList<GetCharacterByIdResponse> = arrayListOf()
    private val viewModel:MyViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    private val TAG = "MyApp"
    private var pageCounterValue = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainView = findViewById<RecyclerView>(R.id.main_list)
        val pageCounter:TextView = findViewById(R.id.pageCounter)
        val startButton: Button = findViewById(R.id.startButton)
        val endButton: Button = findViewById(R.id.endButton)
        pageCounter.text = pageCounterValue.toString()

        mainView.layoutManager = LinearLayoutManager(this)
        val myAdapter = ListAdapter(dataSet,this)
        mainView.adapter = myAdapter
        getOnePage(pageCounterValue);

        viewModel.characterByIdLiveData.observe(this){response->
            if (response==null){
                Toast.makeText(this,"Ooops, date not found",Toast.LENGTH_LONG).show()
                return@observe
            }
            else{
                dataSet.add(response)
//                myAdapter.addItem(response)
//                myAdapter.notifyItemRangeChanged(0,dataSet.size)
                myAdapter.notifyDataSetChanged()
//                myAdapter.notifyItemInserted(dataSet.size-1)
//                if(dataSet.size==6){
//                    myAdapter.notifyItemRangeChanged(0,dataSet.size) // указываем адаптеру новый диапазон элементов
////                    myAdapter.notifyDataSetChanged()
//                }
//
            }
        }

//        viewModel.allCharactersLiveData.observe(this){response->
//            if (response==null){
//                Toast.makeText(this,"Ooops, date not found",Toast.LENGTH_LONG).show()
//                return@observe
//            }
//            else{
//                dataSet=response
////                myAdapter.addItem(response)
////                myAdapter.notifyItemRangeChanged(0,dataSet.size)
//                myAdapter.notifyDataSetChanged()
////                myAdapter.notifyItemInserted(dataSet.size-1)
////                if(dataSet.size==6){
////                    myAdapter.notifyItemRangeChanged(0,dataSet.size) // указываем адаптеру новый диапазон элементов
//////                    myAdapter.notifyDataSetChanged()
////                }
////
//            }
//        }

        startButton.setOnClickListener {
            if (pageCounterValue > 1) {
                dataSet.clear()
                pageCounterValue--
                pageCounter.text = pageCounterValue.toString()
                getOnePage(pageCounterValue);
//                myAdapter.notifyItemInserted(dataSet.size-1)
                myAdapter.notifyDataSetChanged()
            }
        }

        endButton.setOnClickListener {
            dataSet.clear()
            pageCounterValue++
            pageCounter.text = pageCounterValue.toString()
            getOnePage(pageCounterValue);
//            myAdapter.notifyItemInserted(dataSet.size-1)
            myAdapter.notifyDataSetChanged()
        }
//        myAdapter.notifyDataSetChanged()
//


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

    private fun getOnePage(numberPage:Int){
//        viewModel.updatePage(numberPage)
        for (i in (numberPage+5*(numberPage-1))..(numberPage+5+5*(numberPage-1))){
            viewModel.updateCharacter(i)
        }
    }
}

