package com.example.rickandmortyapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
import android.net.ConnectivityManager
import android.util.Log
import android.net.NetworkInfo





class MainActivity : AppCompatActivity() {
    private var dataSet: ArrayList<GetCharacterByIdResponse> = arrayListOf()
    private val viewModel:MyViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    private val TAG = "MyApp"
    private var pageCounterValue = 1
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

        if (isNetworkConnected()){
            getOnePage(pageCounterValue)
            viewModel.allCharactersLiveData.observe(this){response->
                if (response==null){
                    Toast.makeText(this,"Ooops, date not found",Toast.LENGTH_LONG).show()
                    return@observe
                }
                else{
                    dataSet.addAll(response)
                    myAdapter.notifyDataSetChanged()
                }
            }
        }
        else{
            Toast.makeText(this,"There is no internet connection. Turn on the Internet and restart the application",Toast.LENGTH_LONG).show()
        }

        startButton.setOnClickListener {
            if (isNetworkConnected()) {
                if (pageCounterValue > 1) {
                    dataSet.clear()
                    pageCounterValue--
                    pageCounter.text = pageCounterValue.toString()
                    getOnePage(pageCounterValue)
                    myAdapter.notifyDataSetChanged()
                }
            }
        }

        endButton.setOnClickListener {
            if (isNetworkConnected()) {
                dataSet.clear()
                pageCounterValue++
                pageCounter.text = pageCounterValue.toString()
                getOnePage(pageCounterValue)
                myAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getOnePage(numberPage:Int){
        viewModel.getCharacterPage(numberPage)
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

}

