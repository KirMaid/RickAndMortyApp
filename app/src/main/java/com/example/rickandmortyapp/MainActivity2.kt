package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_information)

        val mainPagePhoto: ImageView = findViewById(R.id.mainPagePhoto)
        val mainPageName: TextView = findViewById(R.id.mainPageName)
        val mainPageSpecies: TextView = findViewById(R.id.mainPageSpecies)
        val mainPageGender: TextView = findViewById(R.id.mainPageGender)
        val mainPageStatus:TextView =findViewById(R.id.mainPageStatus)
        val mainPageOrigin:TextView = findViewById(R.id.mainPageOrigin)
        val mainPageLocation:TextView = findViewById(R.id.mainPageLocation)
        val mainPageEpisode:TextView = findViewById(R.id.mainPageEpisode)


        val bundle = intent.extras
        val characterId = bundle?.getInt("index")
        if (characterId != null) {
            viewModel.updateCharacter(characterId)
        }

        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this, "Ooops, date not found", Toast.LENGTH_LONG).show()
                return@observe
            }
            Picasso.get().load(response.image).into(mainPagePhoto)
            mainPageName.text = response.name
            mainPageSpecies.text = response.species
            mainPageLocation.text = response.location.name
            mainPageGender.text = response.gender
            mainPageStatus.text = response.status
            mainPageOrigin.text = response.origin.name
            mainPageEpisode.text = response.episode.size.toString()
        }
    }
}