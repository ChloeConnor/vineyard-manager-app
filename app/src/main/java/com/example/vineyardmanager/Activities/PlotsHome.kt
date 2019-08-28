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

        val vineyardName = intent.getStringExtra("vineyardNameHeader")
        val vineyardID = intent.getLongExtra("vineyardID", -1)

        fab_plots.setOnClickListener { view ->
            intent = Intent(this, CreatePlot::class.java)
            intent.putExtra("vineyardNameHeader", vineyardName)
            intent.putExtra("vineyardID", vineyardID)
            startActivity(intent)
        }

        val recyclerViewPlots = findViewById<RecyclerView>(R.id.recycler_view_plots)
        recyclerViewPlots.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?



        vineyard_name.text = "$vineyardName's plots"

        val plotsToShow: List<Plot> = db.dao().loadPlots()
            .filter { plot -> plot.vineyardID ==  vineyardID}

        if (plotsToShow.isNotEmpty()) {
            val rvAdapterPlots = RvAdapterPlots(plotsToShow)

            println("CHECKPOINT: $plotsToShow")
            recyclerViewPlots.adapter = rvAdapterPlots
        }
    }

}
