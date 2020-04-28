package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavesList extends Fragment {
    RecyclerView recyclerView;
    private SavesListAdapter adapter;
    private List<News> newsList;
    private SavesListAdapter.ItemClickListener listener;
    private SavesListAdapter.FragmentLikeListener fragmentLikeListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new SavesListAdapter.ItemClickListener() {
            @Override
            public void ItemClick(int position, News item) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };
        fragmentLikeListener = new SavesListAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(News news) {
                ((MainActivity)getActivity()).removeItemLike(news);
            }
        };
        newsList= new ArrayList<>();
        adapter = new SavesListAdapter(newsList, listener, fragmentLikeListener);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public void saveNews(News news){
        newsList.add(news);
        recyclerView.getAdapter().notifyItemInserted(newsList.size()-1);
    }
    public void removeNews(News news){
        if (newsList.indexOf(news)==0){
            newsList.remove(news);
        } else {
            int position = newsList.indexOf(news);
            newsList.remove(news);
            recyclerView.getAdapter().notifyItemRemoved(position);
        }
    }
    public void removeLike(News news){
        int n = newsList.indexOf(news);
        this.removeNews(news);
        adapter.notifyItemRemoved(n);
    }
}
