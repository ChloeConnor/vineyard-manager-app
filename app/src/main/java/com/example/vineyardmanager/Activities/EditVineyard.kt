package com.example.vineyardmanager.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vineyardmanager.R
import kotlinx.android.synthetic.main.activity_edit_vineyard.*

class EditVineyard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vineyard)

        val vineyardName = intent.getStringExtra("vineyardNameHeader")

        vineyard_name_for_edit.text = "Edit $vineyardName"
        edit_vineyard_name.setText(vineyardName)

        val budsSwitch = intent.getBooleanExtra("budValue",true)
        val shootSwitch = intent.getBooleanExtra("shootValue",true)
        val flowerSwitch = intent.getBooleanExtra("flowerValue",true)
        val grapeSwitch = intent.getBooleanExtra("grapeValue",true)
        val weightSwitch = intent.getBooleanExtra("weightValue",true)

        Eswitch_buds.isChecked = budsSwitch
        Eswitch_shoots.isChecked = shootSwitch
        Eswitch_flowers.isChecked = flowerSwitch
        Eswitch_grapes.isChecked = grapeSwitch
        Eswitch_weight.isChecked = weightSwitch

    }
}
