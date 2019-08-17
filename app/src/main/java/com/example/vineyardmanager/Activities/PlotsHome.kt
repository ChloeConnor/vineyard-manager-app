package com.example.vineyardmanager.Activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.vineyardmanager.R
import com.example.vineyardmanager.dataTypes.Vineyard

import kotlinx.android.synthetic.main.activity_plots.*

class PlotsHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plots)

        val vineyardName = intent.getStringExtra("vineyardName")
        val vineyardID = intent.getStringExtra("vineyardID")
        vineyard_name.text = "$vineyardName's  Plots"


        fab_plots.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
