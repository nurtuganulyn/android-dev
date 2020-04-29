package com.example.socialnetwork.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import com.example.socialnetwork.base.OnItemClickListener
import com.example.socialnetwork.view_model.PostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var storiesRecyclerView: RecyclerView
    private val postViewModel: PostViewModel by viewModels()
    private var postAdapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindView()
        setAdapter()
        setData()
    }

    private fun bindView() {
        postRecyclerView = findViewById(R.id.postRecyclerView)
        postRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setAdapter() {
        postAdapter = PostAdapter()
        postRecyclerView.adapter = postAdapter

        postAdapter?.setOnItemClickListener(onItemClickListener = object :
            OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                val intent = Intent(baseContext, DetailsActivity::class.java)
                intent.putExtra("post_id", postAdapter?.getItem(position)?.postId)
                startActivity(intent)
            }
        })
    }

    private fun setData() {
        postViewModel.loadPost()
        postViewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is PostViewModel.State.Result -> {
                    postAdapter?.addItems(result.postList)
                }
            }
        })
    }
}
