package com.example.vineyardmanager

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vineyardmanager.Activities.PlotsHome

import com.example.vineyardmanager.dataTypes.Vineyard

class RvAdapterVineyards(val vineyardList: List<Vineyard>) : RecyclerView.Adapter<RvAdapterVineyards.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_item_layout_vineyards, p0, false)

        v.setOnClickListener { view ->
            val intent = Intent(view.context, PlotsHome::class.java)
            intent.putExtra("vineyardNameHeader", vineyardList[p1].name) //FIXME:
            intent.putExtra("vineyardID", vineyardList[p1].vineyardID)
            startActivity(view.context, intent, null)

        }
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return vineyardList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name.text = vineyardList[p1].name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.vineyard_name_input)

    }
}