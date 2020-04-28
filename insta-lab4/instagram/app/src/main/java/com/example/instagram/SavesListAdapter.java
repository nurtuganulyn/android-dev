package com.example.instagram;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class SavesListAdapter extends RecyclerView.Adapter<SavesListAdapter.NewsViewHolder> {
    private List<News> newsList;
    private @Nullable ItemClickListener listener;
    private @Nullable FragmentLikeListener fragmentLikeListener;


    public SavesListAdapter(List<News> newsList,
                           @Nullable ItemClickListener listener,
                           @Nullable FragmentLikeListener fragmentLikeListener) {
        this.newsList = newsList;
        this.listener = listener;
        this.fragmentLikeListener = fragmentLikeListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext())
                .inflate(
                        R.layout.item_news,
                        null,
                        false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        final News news = newsList.get(getItemViewType(position));
        holder.profilePhoto.setImageResource(news.getProfilePhoto());
        holder.postImage.setImageResource(news.getPostImage());
        holder.author.setText(news.getAuthor());
        String s = "<b>"+ news.getAuthor()+ "</b>" + " "+ news.getPostText();
        holder.postText.setText(Html.fromHtml(s));
        holder.date.setText(news.getDate());
        holder.likesCnt.setText(news.getLikesCnt()+" likes");
        holder.likeBtn.setImageResource(R.drawable.liked);

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentLikeListener!=null)
                    fragmentLikeListener.removeItemLike(news);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                    listener.ItemClick(position, news);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView author;
        TextView likesCnt;
        TextView postText;
        TextView date;
        ImageView postImage;
        ImageView profilePhoto;
        ImageView likeBtn;
        ImageView saveBtn;

        public NewsViewHolder(@NonNull final View itemView){
            super(itemView);
            author = itemView.findViewById(R.id.author);
            likesCnt = itemView.findViewById(R.id.likesCnt);
            postImage = itemView.findViewById(R.id.postImage);
            postText = itemView.findViewById(R.id.postText);
            date = itemView.findViewById(R.id.date);
            profilePhoto = itemView.findViewById(R.id.profilePhoto);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            saveBtn = itemView.findViewById(R.id.saveBtn);
        }
    }

    public int getItemViewType(int position){
        return position;
    }

    interface ItemClickListener{
        void ItemClick(int position, News item);
    }
    public interface FragmentLikeListener{
        void removeItemLike(News news);
    }
}


