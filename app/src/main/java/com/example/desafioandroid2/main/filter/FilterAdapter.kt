//package com.example.desafioandroid2.main.filter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Spinner
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.desafioandroid2.R
//import com.example.desafioandroid2.main.model.Item
//
//class FilterAdapter(
//    private val context : Context,
//    private val id : Int,
//    private val item : MutableList<Item>
//) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): FilterAdapter.FilterViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false)
//        return FilterViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: FilterAdapter.FilterViewHolder, position: Int) {
//        val items = item[position]
//        holder.bind(items)
//    }
//
//    override fun getItemCount(): Int {
//        return item.size
//    }
//
//    inner class FilterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//        val filter = view.findViewById<TextView>(R.id.stadiumname)
//
//        fun bind(item : Item){
//            filter.text = item.resource.language_id
//        }
//
//    }
//
//
//}