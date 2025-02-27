package com.example.weddingfinal

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class Memory: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_memory)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        val memoryItems = listOf(
            MemoryItem(R.drawable.node1edit, "Pre Wedding", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
            MemoryItem(R.drawable.memory1   , "Wedding Day", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
            MemoryItem(R.drawable.memory2, "Reception","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
        )
        recyclerView.adapter = MemoryAdapter(this,memoryItems)

        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            private val gestureDetector = GestureDetector(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    if (e1 != null && e2 != null) {
                        val deltaY = e1.y - e2.y
                        println("${velocityY},${deltaY}")
                        if (deltaY > 500 && Math.abs(velocityY) > 1000) { // Swipe up detected
                            val child = recyclerView.findChildViewUnder(e1.x, e1.y)
                            child?.let {
                                val position = recyclerView.getChildAdapterPosition(it)
                                if (position != RecyclerView.NO_POSITION) {
                                    val intent = when (position) {
                                        0 -> Intent(recyclerView.context, Memory1Activity::class.java)
                                        1 -> Intent(recyclerView.context, Memory2Activity::class.java)
                                        2 -> Intent(recyclerView.context, Memory3Activity::class.java)
                                        else -> null
                                    }
                                    intent?.let { recyclerView.context.startActivity(it) }
                                }
                            }
                            return true
                        }
                    }
                    return false
                }
            })

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(e)
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }
}
