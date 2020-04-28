package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    ImageButton back;
    ImageView likeBtn;
    ImageView saveBtn;
    ImageView profilePhoto;
    TextView author;
    ImageView postImage;
    TextView postText;
    TextView date;
    TextView likesCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        back = findViewById(R.id.back);
        likeBtn = findViewById(R.id.likeBtn);
        saveBtn = findViewById(R.id.saveBtn);
        profilePhoto = findViewById(R.id.profilePhoto);
        author = findViewById(R.id.author);
        postImage = findViewById(R.id.postImage);
        postText = findViewById(R.id.postText);
        date = findViewById(R.id.date);
        likesCnt = findViewById(R.id.likesCnt);

        final News news = (News)getIntent().getSerializableExtra("news");

        profilePhoto.setImageResource(news.getProfilePhoto());
        postImage.setImageResource(news.getPostImage());

        author.setText(news.getAuthor());
        String s = "<b>"+ news.getAuthor() + "</b>" + " " + news.getPostText();
        postText.setText(Html.fromHtml(s));
        date.setText(news.getDate());
        likesCnt.setText(news.getLikesCnt()+" likes");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (news.getLikeBtn()==R.drawable.like){
                    likeBtn.setImageResource(R.drawable.liked);
                    news.setLikeBtn(R.drawable.liked);
                } else {
                    likeBtn.setImageResource(R.drawable.like);
                    news.setLikeBtn(R.drawable.like);
                }
            }
        });
    }
}
