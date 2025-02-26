package com.example.weddingfinal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoryAdapter(private val context: Context, private val itemList: List<MemoryItem>) :
    RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>() {

    class MemoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.imageView3)
        val itemTitle: TextView = view.findViewById(R.id.titletv)
        val itemDescription: TextView = view.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memory1, parent, false)
        return MemoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemImage.setImageResource(item.imageResId)
        holder.itemTitle.text = item.title
        holder.itemDescription.text = item.description

        holder.itemView.setOnClickListener {
            val intent = when (position) {
                0 -> Intent(context, Memory1Activity::class.java)  // First item -> PreWeddingActivity
                1 -> Intent(context, Memory2Activity::class.java)  // Second item -> WeddingDayActivity
                2 -> Intent(context, Memory3Activity::class.java)  // Third item -> ReceptionActivity
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }

    override fun getItemCount(): Int = itemList.size
}
