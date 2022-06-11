package com.example.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R

class DashboardRecylerAdapter(val context: Context,val itemList : ArrayList<String>) : RecyclerView.Adapter<DashboardRecylerAdapter.DashboardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val text = itemList[position]
        holder.textView.text = text
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class DashboardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.txtBookName)
    }
}