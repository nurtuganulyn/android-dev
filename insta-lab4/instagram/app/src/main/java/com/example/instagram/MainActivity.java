package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.FragmentButtonListener,
MainAdapter.FragmentLikeListener, SavesListAdapter.FragmentLikeListener{
    private LockableViewPager pager;
    private PagerAdapter pagerAdapter;
    Fragment f1 = new NewsList();
    Fragment f2 = new SavesList();
    List<Fragment> list = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        list.add(f1);
        list.add(f2);
        pager = findViewById(R.id.pager);
        pager.setSwipable(false);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.home:
                                pager.setCurrentItem(0,false);
                                bottomNavigationView.getMenu().findItem(R.id.save).setIcon(R.drawable.ic_save);
                                break;
                            case R.id.save:
                                pager.setCurrentItem(1,false);
                                item.setIcon(R.drawable.ic_favorite);
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void myClick(News news, int option) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.pager);
        if (option==1)
            ((SavesList)fragment).saveNews(news);
        else
            ((SavesList)fragment).removeNews(news);
    }

    @Override
    public void removeItemLike(News news) {
        ((NewsList)f1).removeLike(news);
        ((SavesList)f2).removeLike(news);
    }
}
