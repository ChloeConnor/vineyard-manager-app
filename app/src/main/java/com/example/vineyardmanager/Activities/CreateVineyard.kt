package com.example.vineyardmanager.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vineyardmanager.R
import kotlinx.android.synthetic.main.activity_create_vineyard.*

class CreateVineyard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vineyard)

        button.setOnClickListener {view ->
            val intent = Intent(this, MainActivity::class.java)
            val inputName = edit_vineyard_name.text.toString()
            println("fish: $inputName")
            intent.putExtra("VineyardName", inputName)
            intent.putExtra("Client", "Ben")
            startActivity(intent)

        }
    }
}
