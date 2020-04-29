package com.example.socialnetwork.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.socialnetwork.R
import com.example.socialnetwork.model.PostData
import com.example.socialnetwork.view_model.PostViewModel
import kotlin.properties.Delegates

class DetailsActivity : AppCompatActivity() {

    private lateinit var postLogo: ImageView
    private lateinit var postImage: ImageView
    private lateinit var postAuthor: TextView
    private lateinit var postDescription: TextView
    private lateinit var postLike: TextView
    private lateinit var postComments: TextView
    private lateinit var postTime: TextView
    private lateinit var backButton: ImageButton
    private var postId by Delegates.notNull<Int>()
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        postId = intent.getIntExtra("post_id", 1)
        bindView()
        setData()
    }

    private fun bindView() {
        postLogo = findViewById(R.id.postLogo)
        postImage = findViewById(R.id.postImage)
        postAuthor = findViewById(R.id.postAuthorTextView)
        postDescription = findViewById(R.id.postDescriptionTextView)
        postLike = findViewById(R.id.postLikeTextView)
        postComments = findViewById(R.id.postCommentsTextView)
        postTime = findViewById(R.id.postTimeTextView)
        backButton = findViewById(R.id.backImageButton)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setData() {
        postViewModel.searchPost(postId = postId)
        postViewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is PostViewModel.State.SearchResult -> {
                    setView(result.post)
                }
            }
        })
    }

    private fun setView(postData: PostData) {
        postLogo.setImageResource(postData.postLogo)
        postImage.setImageResource(postData.postImage)
        postAuthor.text = postData.postAuthor
        postLike.text = "Нравиться: " + postData.postLike.toString()
        postDescription.text = postAuthor.text as String + " " + postData.postDescription
        postComments.text = postData.postComments
        postTime.text = postData.postTime
    }

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
}