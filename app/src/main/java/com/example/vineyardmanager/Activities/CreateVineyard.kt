package com.example.vineyardmanager.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vineyardmanager.R
import com.example.vineyardmanager.dataTypes.Vineyard
import kotlinx.android.synthetic.main.activity_create_vineyard.*
import kotlinx.android.synthetic.main.activity_create_vineyard.view.*
import kotlinx.android.synthetic.main.activity_main.view.*

class CreateVineyard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vineyard)

        button.setOnClickListener {view ->
            val intent = Intent(this, MainActivity::class.java)
            val input = edit_vineyard_name.text.toString()
            intent.putExtra("VineyardName", input)
            startActivity(intent)

        }
    }
}
