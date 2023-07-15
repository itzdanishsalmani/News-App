package com.example.newsfetch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView's layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = fetchData()
        val adapter = NewsListAdapter(items,this) //items in adapter

        //linking adapter and recyclerview
        recyclerView.adapter = adapter
    }

    private fun fetchData():ArrayList<String>{
        val list = ArrayList<String>()
        for (i in 0 until 100 ){
            list.add("Item $i")
        }
        return list
    }
    //functions of interface
    override fun onItemClicked(item: String) {
        Toast.makeText(this,"Clicked item is $item",Toast.LENGTH_LONG).show()
    }
}