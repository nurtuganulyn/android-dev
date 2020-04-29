package com.example.kotlinhw4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: NewsListAdapter? = null
    private lateinit var listener: NewsListAdapter.ItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listener = object : ItemClickListener, NewsListAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News?) {
                val intent = Intent(this@MainActivity, NewsOnClickActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)
            }

            override fun itemClick(view: View?, position: Int) {
                fun itemClick(view: View?, position: Int) {

                }
            }
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(NewsContent.getNews(), listener, this)
        recyclerView.adapter = adapter
    }
}