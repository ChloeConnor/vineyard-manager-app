package com.example.vineyardmanager.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vineyardmanager.R
import com.example.vineyardmanager.RvAdapter
import com.example.vineyardmanager.dataTypes.Vineyard
import com.example.vineyardmanager.database.VineyardManagerDatabase
import kotlinx.android.synthetic.main.activity_plots.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = VineyardManagerDatabase.getAppDatabase(this)

        fab_plots.setOnClickListener { view ->
            intent = Intent(this, CreateVineyard::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val newVineyard = Vineyard(
            name = intent.getStringExtra("VineyardName"),
            client = intent.getStringExtra("Client"),
            countBuds = intent.getBooleanExtra("CountBuds", true),
            countShoots = intent.getBooleanExtra("CountShoots", true),
            countFlowers = intent.getBooleanExtra("CountFlowers", true),
            countGrapes = intent.getBooleanExtra("CountGrapes", true),
            weight = intent.getBooleanExtra("Weight", true)
        )
        println("newVineyard: $newVineyard")

        if (newVineyard.name != null && newVineyard.name != "") {
            db.dao().insertVineyard(newVineyard)
        }

        val vineyardsToShow: List<Vineyard> = db.dao().loadVineyards()
        println("vineyardsToShow: $vineyardsToShow")

        val rvAdapter = RvAdapter(vineyardsToShow)

        recyclerView.adapter = rvAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
