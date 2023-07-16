package com.example.newsfetch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    //return type is ViewHolder

    private val items:ArrayList<News> = ArrayList() //ArrayList<News> is a type of data which is going on Adapter

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

    override fun getItemCount(): Int {
        //count no. of items
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //bind data with holder and current items
        val currentItem=items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
    fun updateNews(updateNews:ArrayList<News>){   //for passing News data with function to adapter
        items.clear()
        items.addAll(updateNews)

        notifyDataSetChanged()

    }

}
//viewHolder is passed on Adapter
class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    //items of news.xml is here and then convert into View in Adapter
    val titleView:TextView = itemView.findViewById<TextView>(R.id.title)
    val image:ImageView = itemView.findViewById<ImageView>(R.id.image)
    val author:TextView = itemView.findViewById<TextView>(R.id.author)
}

//instance of interface is passed on NewsListAdapter by val listener
interface NewsItemClicked{
    fun onItemClicked(item:News)
}