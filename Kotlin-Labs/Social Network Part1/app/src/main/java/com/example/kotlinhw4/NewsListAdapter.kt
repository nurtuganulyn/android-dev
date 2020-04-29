package com.example.kotlinhw4

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class NewsListAdapter(
    private val newsList: List<News>,
    private val listener: ItemClickListener?,
    private var c: Context
) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    private var clicked = false

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, null, false)
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return NewsViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val news = newsList[position]
        holder.tvName.text = news.getName()
        holder.tvDate.text = news.getDate()
        holder.tvText.text = news.getText()
        holder.tvViewsCount.text = (news.getViewsCount()).toString()
        holder.tvRepostCount.text = (news.getRepostsCount()).toString()
        holder.tvLikesCount.text = (news.getLikesCount()).toString()
        holder.tvCommentsCount.text = (news.getCommentsCount()).toString()
        holder.tvImage.setImageResource(news.getImg()).toString()
        holder.itemView.setOnClickListener { listener?.itemClick(position, news) }
        holder.like.setOnClickListener {
            if (!clicked) {
                holder.like.setImageResource(R.drawable.blueheart)
                val s = holder.tvLikesCount.text.toString().toInt() + 1
                holder.tvLikesCount.text = s.toString()
                holder.tvLikesCount.setTextColor(Color.parseColor("#3B6FA1"))
                val toast =
                    Toast.makeText(c, "Добавлено в избранное", Toast.LENGTH_SHORT)
                toast.show()
                clicked = true
                holder.like.setOnClickListener {
                    if (!clicked) {
                        holder.like.setImageResource(R.drawable.blueheart)
                        val s = holder.tvLikesCount.text.toString().toInt() + 1
                        holder.tvLikesCount.text = s.toString()
                        holder.tvLikesCount.setTextColor(Color.parseColor("#3B6FA1"))
                        val toast =
                            Toast.makeText(c, "Добавлено в избранное", Toast.LENGTH_SHORT)
                        toast.show()
                        clicked = true
                    } else {
                        holder.like.setImageResource(R.drawable.heart)
                        val s = holder.tvLikesCount.text.toString().toInt() - 1
                        holder.tvLikesCount.text = s.toString()
                        holder.tvLikesCount.setTextColor(Color.parseColor("#A6A9B0"))
                        clicked = false
                    }
                }
            } else {
                holder.like.setImageResource(R.drawable.heart)
                val s = holder.tvLikesCount.text.toString().toInt() - 1
                holder.tvLikesCount.text = s.toString()
                holder.tvLikesCount.setTextColor(Color.parseColor("#A6A9B0"))
                clicked = false
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    //newsHolder class
    inner class NewsViewHolder internal constructor(itemView: View) :
        ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.autor)
        var tvDate: TextView = itemView.findViewById(R.id.date)
        var tvText: TextView = itemView.findViewById(R.id.vText)
        var tvCommentsCount: TextView = itemView.findViewById(R.id.comment_text)
        var tvLikesCount: TextView = itemView.findViewById(R.id.like_text)
        var tvRepostCount: TextView = itemView.findViewById(R.id.shre_text)
        var tvViewsCount: TextView = itemView.findViewById(R.id.view_text)
        var tvImage: ImageView = itemView.findViewById(R.id.imageView3)
        val like: ImageView = itemView.findViewById(R.id.like_btn)
    }

    interface ItemClickListener {
        fun itemClick(position: Int, item: News?)
    }
}