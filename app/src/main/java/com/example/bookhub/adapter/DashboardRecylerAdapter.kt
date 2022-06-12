package com.example.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import com.example.bookhub.model.Book

class DashboardRecylerAdapter(val context: Context,val itemList : ArrayList<Book>) : RecyclerView.Adapter<DashboardRecylerAdapter.DashboardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.textBookName.text = book.BookName
        holder.textBookAuthor.text = book.BookAuthor
        holder.textBookPrice.text = book.BookCost
        holder.textBookRating.text = book.BookRating
        holder.textBookImage.setImageResource(book.BookImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class DashboardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textBookName: TextView = view.findViewById(R.id.txtBookName)
        val textBookAuthor : TextView = view.findViewById(R.id.txtBookAuthor)
        val textBookPrice : TextView = view.findViewById(R.id.txtBookPrice)
        val textBookRating : TextView = view.findViewById(R.id.txtBookRating)
        val textBookImage : ImageView = view.findViewById(R.id.imgBookImage)
    }
}