package com.example.vineyardmanager.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vineyardmanager.R
import com.example.vineyardmanager.dataTypes.Plot
import com.example.vineyardmanager.database.VineyardManagerDatabase
import kotlinx.android.synthetic.main.activity_create_plot.*

class CreatePlot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_plot)
        val db = VineyardManagerDatabase.getAppDatabase(this)

        plot_confirm_button.setOnClickListener{view ->
            val intent = Intent(this, PlotsHome::class.java)

            val plotName = add_plot_name.text.toString()
            println("plot name: $plotName")

            val newPlot = Plot(
                name = plotName,
                vineyardID = intent.getLongExtra("vineyardID", 0)
            )

            if (newPlot.name != null && newPlot.name != "") {
                db.dao().insertPlot(newPlot)
            }

            startActivity(intent)
        }
    }
}
