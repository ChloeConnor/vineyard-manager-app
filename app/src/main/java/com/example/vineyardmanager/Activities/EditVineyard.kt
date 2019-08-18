package com.example.vineyardmanager.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vineyardmanager.R
import kotlinx.android.synthetic.main.activity_edit_vineyard.*

class EditVineyard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vineyard)

        val vineyardName = intent.getStringExtra("vineyardNameHeader")
        vineyard_name_for_edit.text = "Edit $vineyardName"

    }
}
