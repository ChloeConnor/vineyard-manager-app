package com.example.vineyardmanager.Activities

import android.content.Context
import android.content.Intent
import android.net.sip.SipSession
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vineyardmanager.R
import com.example.vineyardmanager.RvAdapterVineyards
import com.example.vineyardmanager.dataTypes.Vineyard
import com.example.vineyardmanager.database.VineyardManagerDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = VineyardManagerDatabase.getAppDatabase(this)

        fab_vineyards.setOnClickListener { view ->
            intent = Intent(this, CreateVineyard::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_vineyards)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        val vineyardsToShow: List<Vineyard> = db.dao().loadVineyards()
        println("vineyardsToShow: $vineyardsToShow")

        val rvAdapter = RvAdapterVineyards(vineyardsToShow)

        recyclerView.adapter = rvAdapter

        recyclerView.addOnItemTouchListener(RecyclerTouchListener(
            applicationContext,
            recyclerView, object : ClickListener {

            override fun onClick(view: View, position: Int) {
                val intent = Intent(view.context, PlotsHome::class.java)
                intent.putExtra("vineyardNameHeader", vineyardsToShow[position].name)
                intent.putExtra("vineyardID", vineyardsToShow[position].vineyardID)
                ContextCompat.startActivity(view.context, intent, null)
                Toast.makeText(this@MainActivity, vineyardsToShow[position].name + "'s plots", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(view: View, position: Int) {
                val intent = Intent(view.context, EditVineyard::class.java)
                intent.putExtra("vineyardNameHeader", vineyardsToShow[position].name)
                intent.putExtra("vineyardID", vineyardsToShow[position].vineyardID)
                Toast.makeText(this@MainActivity, "Edit " + vineyardsToShow[position].name, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }}))}

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
    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View, position: Int)
    }

    class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        val clickListener: ClickListener?)
        :RecyclerView.OnItemTouchListener {

        private var gestureDetector: GestureDetector? = null

        init {
            gestureDetector = GestureDetector(
                context,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent?): Boolean {
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val child = recyclerView.findChildViewUnder(e.x, e.y)
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(
                                child,
                                recyclerView.getChildAdapterPosition(child)
                            )
                        }
                    }
                })
        }


        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector != null && gestureDetector!!.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }