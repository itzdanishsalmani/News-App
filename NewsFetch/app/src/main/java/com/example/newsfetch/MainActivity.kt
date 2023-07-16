package com.example.newsfetch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest as JsonObjectRequest


class MainActivity : AppCompatActivity(), NewsItemClicked {
    private  lateinit var mAdapter: NewsListAdapter
    //mAdapter is member function which is accessible to everywhere
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView's layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()
        mAdapter = NewsListAdapter(this) //items in adapter

        //linking adapter and recyclerview
        recyclerView.adapter = mAdapter
    }

    private fun fetchData(){
        val url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c744ac70a5c04a1c89860dcc11f55246"
       val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
           {
               val newsJsonArray = it.getJSONArray("articles")// extract from API articles is Array
               val newsArray = ArrayList<News>()
               for (i in 0 until newsJsonArray.length()){
                   val newsJsonObject = newsJsonArray.getJSONObject(i)
                   val news=News(
                       newsJsonObject.getString("title"),
                               newsJsonObject.getString("author"),
                               newsJsonObject.getString("url"),
                               newsJsonObject.getString("urlToImage")
                   )
                   newsArray.add(news)
               }
               mAdapter.updateNews(newsArray) //passing newsArray into Adapter
           },
           {

               }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    //functions of interface
    override fun onItemClicked(item: News) {
    }
}