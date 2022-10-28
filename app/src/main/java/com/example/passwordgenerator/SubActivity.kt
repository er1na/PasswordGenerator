package com.example.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val toMainButton = findViewById<TextView>(R.id.generate)
        val toMainListener =  ToMainActivityTrans()
        toMainButton.setOnClickListener(toMainListener)

        val passwordList = findViewById<ListView>(R.id.passwordList)

    }

    private inner class ToMainActivityTrans: View.OnClickListener{
        override  fun onClick(view: View){
            finish()
        }
    }


}