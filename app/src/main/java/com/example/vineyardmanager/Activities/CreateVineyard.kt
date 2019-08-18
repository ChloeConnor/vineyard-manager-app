package com.example.vineyardmanager.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.vineyardmanager.R
import kotlinx.android.synthetic.main.activity_create_vineyard.*

class CreateVineyard : AppCompatActivity() {

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vineyard)

        vineyard_confirm_button.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)

            //FIXME: need a value for client
            intent.putExtra("Client", "client")

            val inputName = edit_vineyard_name.text.toString()
            intent.putExtra("VineyardName", inputName)

            val budsInput = switch_buds.isChecked
            intent.putExtra("CountBuds", budsInput)

            val shootsInput = switch_shoots.isChecked
            intent.putExtra("CountShoots", shootsInput)

            val flowersInput = switch_flowers.isChecked
            intent.putExtra("CountFlowers", flowersInput)

            val grapesInput = switch_grapes.isChecked
            intent.putExtra("CountGrapes", grapesInput)

            val weightInput = switch_weight.isChecked
            intent.putExtra("Weight", weightInput)

            startActivity(intent)

        }
    }
}
