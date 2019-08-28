package com.example.vineyardmanager.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.vineyardmanager.R
import com.example.vineyardmanager.dataTypes.Plot
import com.example.vineyardmanager.database.VineyardManagerDatabase
import kotlinx.android.synthetic.main.activity_create_plot.*

class CreatePlot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_plot)
        val db = VineyardManagerDatabase.getAppDatabase(this)


        val vineyardID = intent.getLongExtra("vineyardID", -1)
        val vineyardName = intent.getStringExtra("vineyardNameHeader")

        println("giraffe: $vineyardName")

            val plotName = add_plot_name.text.toString()
            println("plot name: $plotName")


            val newPlot = Plot(
                name = plotName,
                vineyardID = vineyardID
            )

            if (newPlot.name != null && newPlot.name != "") {
                db.dao().insertPlot(newPlot)
            }

            val intent = Intent(this, PlotsHome::class.java)

            intent.putExtra("vineyardNameHeader", vineyardName)
            intent.putExtra("vineyardID", vineyardID)
            println(db.dao().loadPlots())

        plot_confirm_button.setOnClickListener{view ->
            ContextCompat.startActivity(view.context, intent, null)
        }
    }
}
