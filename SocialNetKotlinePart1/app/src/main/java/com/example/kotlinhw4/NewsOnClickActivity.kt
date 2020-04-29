package com.example.kotlinhw4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NewsOnClickActivity : AppCompatActivity() {
    private lateinit var otvName: TextView
    private lateinit var otvDate: TextView
    private lateinit var otvText: TextView
    private lateinit var otvCommentsCount: TextView
    private lateinit var otvLikesCount: TextView
    private lateinit var otvRepostCount: TextView
    private lateinit var otvViewsCount: TextView
    private lateinit var likedByPeople: TextView
    private lateinit var otvImage: ImageView
    private lateinit var news: News
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_on_click)

        //  ActionBar actionBar = getSupportActionBar();
        otvName = findViewById(R.id.autor)
        otvDate = findViewById(R.id.date)
        otvText = findViewById(R.id.vText)
        otvCommentsCount = findViewById(R.id.comment_text)
        otvLikesCount = findViewById(R.id.like_text)
        otvRepostCount = findViewById(R.id.shre_text)
        otvViewsCount = findViewById(R.id.view_text)
        likedByPeople = findViewById(R.id.likedbypeople)
        otvImage = findViewById(R.id.imageView3)
        news = intent.getParcelableExtra("news")
        otvName.text = news.getName()
        otvDate.text = news.getDate()
        otvText.text = news.getText()
        otvViewsCount.text = news.getCommentsCount().toString()
        otvRepostCount.text = news.getRepostsCount().toString()
        otvLikesCount.text = news.getLikesCount().toString()
        otvCommentsCount.text = news.getCommentsCount().toString()
        likedByPeople.text = "Понравилось " + news.getLikesCount().toString() + " людям"
        otvImage.setImageResource(news.getImg())
    }
}
