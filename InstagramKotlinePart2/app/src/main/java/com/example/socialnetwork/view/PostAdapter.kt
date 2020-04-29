package com.example.socialnetwork.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.R
import com.example.socialnetwork.base.BaseRecyclerViewAdapter
import com.example.socialnetwork.model.PostData

class PostAdapter : BaseRecyclerViewAdapter<PostData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder = holder as PostViewHolder
        getItem(position)?.let { myHolder.bindView(postData = it) }
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var postLogo = itemView.findViewById<ImageView>(R.id.postLogo)
        private var postImage = itemView.findViewById<ImageView>(R.id.postImage)
        private var postAuthor = itemView.findViewById<TextView>(R.id.postAuthorTextView)
        private var postLike = itemView.findViewById<TextView>(R.id.postLikeTextView)
        private var postDescription = itemView.findViewById<TextView>(R.id.postDescriptionTextView)
        private var postComments = itemView.findViewById<TextView>(R.id.postCommentsTextView)
        private var postTime = itemView.findViewById<TextView>(R.id.postTimeTextView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(postData: PostData) {
            postLogo.setImageResource(postData.postLogo)
            postImage.setImageResource(postData.postImage)
            postAuthor.text = postData.postAuthor
            postLike.text = "Нравиться: " + postData.postLike.toString()
            postDescription.text = postAuthor.text as String + " " + postData.postDescription
            postComments.text = postData.postComments
            postTime.text = postData.postTime
        }

        override fun onClick(v: View?) {
            if (v != null) {
                itemClickListener?.onItemClick(adapterPosition, v)
            }
        }
    }
}