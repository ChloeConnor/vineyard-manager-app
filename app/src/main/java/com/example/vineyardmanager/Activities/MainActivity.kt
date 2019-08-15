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
import kotlinx.android.synthetic.main.activity_plots.*
import java.io.File
import java.io.FileInputStream



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            intent = Intent(this, CreateVineyard::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val path = getExternalFilesDir(null)
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "Records.txt")

        val vineyardName: String? = intent.getStringExtra("VineyardName")

        if (vineyardName != null) {
            file.appendText("$vineyardName;;")
        }

        var readInVineyards = ArrayList<String>()
        var vineyardsToShow = ArrayList<Vineyard>()

        if (file.exists()) {
            readInVineyards = ArrayList(FileInputStream(file)
                .bufferedReader()
                .use { it.readText() }
                .split(";;"))
        }

        if (readInVineyards.isNotEmpty()) {
        for (vineyard in readInVineyards) {
            vineyardsToShow.add(Vineyard(vineyard))
        }}

        vineyardsToShow = ArrayList(vineyardsToShow.dropLast(1))

        println("VINEYARDS TO SHOW: $vineyardsToShow")
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
