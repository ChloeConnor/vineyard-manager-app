package com.example.vineyardmanager

import android.content.Intent
import com.example.vineyardmanager.dataTypes.Plot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vineyardmanager.Activities.PlotsHome


class RvAdapterPlots(val plotList: List<Plot>) : RecyclerView.Adapter<RvAdapterPlots.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_item_layout_vineyards, p0, false)

        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return plotList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name.text = plotList[p1].name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.plot_name)

    }
}