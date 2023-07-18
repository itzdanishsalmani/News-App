package com.example.newsfetch

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
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
        //val url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=10d82ec53a17473f8717dc0465796e34"
        val url = "https://gnews.io/api/v4/search?q=example&lang=en&country=in&max=50&apikey=6612c78e62501f50ddb9d420ad9c78f1"
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
                               newsJsonObject.getString("publishedAt"),
                               newsJsonObject.getString("url"),
                               newsJsonObject.getString("image")
                   )
                   newsArray.add(news)
               }
               mAdapter.updateNews(newsArray) //passing newsArray into Adapter
               mAdapter.notifyDataSetChanged()
           },
           {

           }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    //functions of interface
    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}