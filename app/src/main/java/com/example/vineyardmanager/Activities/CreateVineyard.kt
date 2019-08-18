package com.example.vineyardmanager.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.vineyardmanager.R
import com.example.vineyardmanager.dataTypes.Vineyard
import com.example.vineyardmanager.database.VineyardManagerDatabase
import kotlinx.android.synthetic.main.activity_create_vineyard.*

class CreateVineyard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vineyard)

        vineyard_confirm_button.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)

            val inputName = edit_vineyard_name.text.toString()
            val budsInput = switch_buds.isChecked
            val shootsInput = switch_shoots.isChecked
            val flowersInput = switch_flowers.isChecked
            val grapesInput = switch_grapes.isChecked
            val weightInput = switch_weight.isChecked

            val newVineyard = Vineyard(
                name = inputName,
                //FIXME: need a value for client
                client = "Client",
                countBuds = budsInput,
                countShoots = shootsInput,
                countFlowers = flowersInput,
                countGrapes = grapesInput,
                weight = weightInput
            )
            println("newVineyard: $newVineyard")

            val db = VineyardManagerDatabase.getAppDatabase(this)

            if (newVineyard.name != null && newVineyard.name != "") {
                db.dao().insertVineyard(newVineyard)
            }

            startActivity(intent)

        }
    }
}
