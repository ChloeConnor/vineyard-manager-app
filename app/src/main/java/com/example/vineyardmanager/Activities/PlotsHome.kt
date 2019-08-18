package com.example.vineyardmanager.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vineyardmanager.R
import com.example.vineyardmanager.RvAdapterPlots
import com.example.vineyardmanager.dataTypes.Plot
import com.example.vineyardmanager.database.VineyardManagerDatabase

import kotlinx.android.synthetic.main.activity_plots.*

class PlotsHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plots)

        val db = VineyardManagerDatabase.getAppDatabase(this)

        fab_plots.setOnClickListener { view ->
            intent = Intent(this, CreatePlot::class.java)
            startActivity(intent)
        }

        val recyclerViewPlots = findViewById<RecyclerView>(R.id.recycler_view_plots)
        recyclerViewPlots.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val vineyardName = intent.getStringExtra("vineyardNameHeader")
        val plotName = intent.getStringExtra("plotName")
        val vineyardID = intent.getLongExtra("vineyardID", 0)
        vineyard_name.text = "$vineyardName's Plots"

        val newPlot = Plot(
            vineyardID = vineyardID,
            name = plotName
        )

        if (newPlot.name != null && newPlot.name != "") {
            db.dao().insertPlot(newPlot)
        }

        val plotsToShow: List<Plot> = db.dao().loadPlots()

        val rvAdapter = RvAdapterPlots(plotsToShow)

        recyclerViewPlots.adapter = rvAdapter

    }

}
