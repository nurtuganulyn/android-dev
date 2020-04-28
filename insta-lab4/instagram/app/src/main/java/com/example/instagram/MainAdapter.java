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


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.NewsViewHolder> {

    List<News> main_list;
    private @Nullable ItemClickListener listener;
    private @Nullable FragmentButtonListener fragmentButtonListener;
    private @Nullable FragmentLikeListener fragmentLikeListener;

    public MainAdapter(List<News> newsList,
                       @Nullable ItemClickListener listener,
                       @Nullable FragmentButtonListener fragmentButtonListener,
                       @Nullable FragmentLikeListener fragmentLikeListener) {
        News.newsList = newsList;
        main_list = newsList;
        this.listener = listener;
        this.fragmentButtonListener = fragmentButtonListener;
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
        final News news = News.newsList.get(getItemViewType(position));
        holder.profilePhoto.setImageResource(news.getProfilePhoto());
        holder.postImage.setImageResource(news.getPostImage());
        holder.author.setText(news.getAuthor());
        String s = "<b>"+ news.getAuthor()+ "</b>" + " "+ news.getPostText();
        holder.postText.setText(Html.fromHtml(s));
        holder.date.setText(news.getDate());
        holder.likesCnt.setText(news.getLikesCnt()+" likes");

        if (news.isLiked()==true)holder.likeBtn.setImageResource(R.drawable.liked);
        else holder.likeBtn.setImageResource(R.drawable.like);

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentButtonListener!=null){
                    if (news.isLiked()){
                        holder.likeBtn.setImageResource(R.drawable.like);
                        //fragmentButtonListener.myClick(news, 2);
                        fragmentLikeListener.removeItemLike(news);
                        news.setLiked(false);
                    } else
                    {
                        holder.likeBtn.setImageResource(R.drawable.liked);
                        fragmentButtonListener.myClick(news, 1);
                        news.setLiked(true);
                    }
                }
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
        return News.newsList.size();
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

    public interface FragmentButtonListener{
        void myClick(News news, int option);
    }

    interface ItemClickListener{
        void ItemClick(int position, News item);
    }
    public interface FragmentLikeListener{
        void removeItemLike(News news);
    }

    public void removeLike(News news){
        int n = News.newsList.indexOf(news);
        news.setLiked(false);
        news.setLikeBtn(R.drawable.like);
        News.newsList.set(n, news);
        main_list.set(n, news);
        this.notifyItemChanged(n);
    }
}

