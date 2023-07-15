package com.example.newsfetch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val items:ArrayList<String>,private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    //return type is ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    //call when onCreate
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news, parent , false)
        //LayoutInflater convert xml into View which in val view

        val viewHolder = NewsViewHolder(view)
        //Listener is handled by Activity's  duty not Adapter
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    //bind data with holder and current items
        val currentItem=items[position]
        holder.titleView.text = currentItem
    }

    override fun getItemCount(): Int {
        //count no. of items
        return items.size
    }
}
//viewHolder is passed on Adapter
class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView:TextView = itemView.findViewById<TextView>(R.id.title)
    //items of news.xml is here and then convert into View in Adapter
}

//instance of interface is passed on NewsListAdapter by val listener
interface NewsItemClicked{
    fun onItemClicked(item:String)
}