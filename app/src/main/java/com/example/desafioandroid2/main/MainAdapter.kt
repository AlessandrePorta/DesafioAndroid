package com.example.desafioandroid2.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.Resources

class MainAdapter(
    private val items: List<Resources>,
    private val context: Context,
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_details_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val value = itemView.findViewById<TextView>(R.id.tv_value)
        val resource_id = itemView.findViewById<TextView>(R.id.tv_resourceid)
        val updated_at = itemView.findViewById<TextView>(R.id.tv_updateat)

        fun bind(item: Resources) {

            value.text = item.value
            resource_id.text = item.resource_id
            updated_at.text = item.updated_at

        }
    }

}