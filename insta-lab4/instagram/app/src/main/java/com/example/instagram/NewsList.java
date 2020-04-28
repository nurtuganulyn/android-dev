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

public class NewsList extends Fragment {

    RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainAdapter.ItemClickListener listener;
    private MainAdapter.FragmentButtonListener fragmentButtonListener = null;
    private MainAdapter.FragmentLikeListener fragmentLikeListener = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new MainAdapter.ItemClickListener() {
            @Override
            public void ItemClick(int position, News item) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };
        fragmentButtonListener = new MainAdapter.FragmentButtonListener() {
            @Override
            public void myClick(News news, int option) {
                ((MainActivity) getActivity()).myClick(news, option);
            }
        };
        fragmentLikeListener = new MainAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(News news) {
                ((MainActivity)getActivity()).removeItemLike(news);
            }
        };
        mainAdapter = new MainAdapter(newsGenerator(), listener, fragmentButtonListener, fragmentLikeListener);
        recyclerView.setAdapter(mainAdapter);
        return rootView;
    }

    private List<News> newsGenerator(){
        List<News> items = new ArrayList<>();
        News news1 = new News(
                1,
                "brfootball",
                "April 17, 2019",
                R.drawable.profile2,
                623462,
                R.drawable.post1,
                "Why do you love football?\n" +
                        "What do you see in it?\n" +
                        "Why not watch another sport\n" +
                        "Like Rugby, Golf, or Cricket?\n" +
                        "\n" +
                        "Well let me answer that my friend\n" +
                        "And put your mind at rest\n" +
                        "I love the beautiful game\n" +
                        "Because it's simply the best\n" +
                        "\n" +
                        "No other sport is as exciting\n" +
                        "No other comes anywhere near\n" +
                        "Football can create passion\n" +
                        "And lots of atmosphere" +
                        "#FootballIsLife #LoveFootball #PlayFootball"
        );
        items.add(news1);
        News news2 = new News(
                2,
                "brfootball",
                "September 1, 2019",
                R.drawable.profile2,
                2361512,
                R.drawable.post2,
                "Well...\n" +
                        "You can\n" +
                        "kick it you can catch\n" +
                        "it you can bounce it - all\n" +
                        "around. You can grab it you can\n" +
                        "pat it you can roll it - on the ground."
        );
        items.add(news2);
        News news3 = new News(
                3,
                "brfootball",
                "May 2, 2019",
                R.drawable.profile2,
                3213141,
                R.drawable.post3,
                "In a pub all the channels were on football\n" +
                        "But there was a guy with no interest at all\n" +
                        "He was asked; who’s winning?\n" +
                        "He replied; dunno who’s playing?\n" +
                        "He only knew when his beer was due for a call"
        );
        items.add(news3);
        News news4 = new News (
                4,
                "brfootball",
                "May 31, 2018",
                R.drawable.profile2,
                6234234,
                R.drawable.post4,
                "I can’t forget those days baby\n" +
                        "No I can't forget at all\n" +
                        "When you used to love me madly\n" +
                        "When I was only your doll!\n"
        );
        items.add(news4);
        News news5 = new News (
                5,
                "brfootball",
                "April 29, 2019",
                R.drawable.profile2,
                5235233,
                R.drawable.post5,
                "You have loose character\n" +
                        "I have known it later\n" +
                        "It has driven me crazy\n" +
                        "Baby I feel so lonely!"
        );
        items.add(news5);
        News news6 = new News (
                6,
                "brfootball",
                "March 27, 2019",
                R.drawable.profile2,
                25332,
                R.drawable.post6,
                "Baby I am not bad at all\n" +
                        "But you treat me like a football\n" +
                        "That’s why now I curse you\n" +
                        "But I pray for you too!"
        );
        items.add(news6);
        News news7 = new News (
                7,
        "brfootball",
                "March 15, 2019",
                R.drawable.profile2,
                24234253,
                R.drawable.post7,
                "Baby now I hate you\n" +
                        "As much as I love you\n" +
                        "You are like my favorite food\n" +
                        "Baby I am not very good!"
        );
        items.add(news7);
        News news8 = new News(
                8,
                "brfootball",
                "November 20, 2018",
                R.drawable.profile2,
                6457456,
                R.drawable.post8,
                "YOU can keep your antique silver and your statuettes of bronze,\n" +
                        "Your curios and tapestries so fine,\n" +
                        "But of all your treasures rare there is nothing to compare\n" +
                        "With this patched up, wornout football pal o’ mine.\n" +
                        "Just a patchedup wornout football, yet how it clings!\n" +
                        "I live again my happier days in thoughts that football brings."
        );
        items.add(news8);
        News news9 = new News (
                9,
                "brfootball",
                "January 3",
                R.drawable.profile2,
                8474,
                R.drawable.post9,
                "Now rule number one\n" +
                        "Football is made of eleven strong men\n" +
                        "When one or two are naughty\n" +
                        "you may still get away\n" +
                        "with playing nine or ten\n" +
                        "\n"
        );
        items.add(news9);
        News news10 = new News (
                10,
                "brfootball",
                "January 6",
                R.drawable.profile2,
                10716,
                R.drawable.post10,
                "Rule number two\n" +
                        "When a member of your crew\n" +
                        "hits the ball into the net\n" +
                        "A goal is scored\n" +
                        "that I can place a bet\n" +
                        "For I am very sure"
        );
        items.add(news10);
        News news11 = new News(
                11,
                "433",
                "March 6",
                R.drawable.profile1,
                18857,
                R.drawable.post11,
                "Come On You Gunners\uD83E" +
                        "#YaGunnersYa #COYG #RedAndWhite");
        items.add(news11);
        News news12 = new News(
                12,
                "polymathematica",
                "February 20",
                R.drawable.profile1,
                25809,
                R.drawable.post12,
                "Rule number three\n" +
                        "The maximum number of players\n" +
                        "you can substitute is three\n" +
                        "It does not cost a dime\n" +
                        "It is totally free"
        );
        items.add(news12);
        News news13 = new News(
                13,
                "433",
                "January 12",
                R.drawable.profile1,
                6792,
                R.drawable.post13,
                "Rule number four\n" +
                        "There are rules you must comply with\n" +
                        "When a team commits an offense\n" +
                        "named a foul\n" +
                        "before the game can commence\n" +
                        "A small advantage has to be given\n" +
                        "to the otherside"
        );
        items.add(news13);
        News news14 = new News(
                14,
                "433",
                "January 12",
                R.drawable.profile1,
                7300,
                R.drawable.post14,
                "Rule number five\n" +
                        "A freekick\n" +
                        "is given after a foul\n" +
                        "Sometimes awarded after\n" +
                        "unnecessary dives"
        );
        items.add(news14);
        News news15 = new News(
                15,
                "433",
                "January 8",
                R.drawable.profile1,
                944583,
                R.drawable.post15,
                "Listen to Pogba and Dybala\n" +
                        "@paulpogba @dybala #StayAtHome #Pogba #Dybala #433"
        );
        items.add(news15);
        News news16 = new News(
                16,
                "433",
                "December 3, 2019",
        R.drawable.profile1,
                3016,
                R.drawable.post16,
                "Listen to Pogba and Dybala\n" +
                        "@paulpogba @dybala #StayAtHome #Pogba #Dybala #433"

        );
        items.add(news16);
        News news17 = new News(
                17,
                "433",
                "December 9, 2019",
                R.drawable.profile1,
                9393,
                R.drawable.post17,
                "Simeone says hello to y'all"
        );
        items.add(news17);
        News news18 = new News(
                18,
                "433",
                "June 7, 2019",
                R.drawable.profile1,
                2260,
                R.drawable.post18,
                "We are all Simeone sometimes.."
        );
        items.add(news18);
        News news19 = new News(
                19,
                "433",
                "April 19, 2019",
                R.drawable.profile1,
                113189,
                R.drawable.post19,
                "Watch your feet! Atletico defeated Liverpool!" +
                        "#Atletico #CelebrateLikeASimeone"
        );
        items.add(news19);
        News news20 = new News(
                20,
                "433",
                "December 23, 2018",
                R.drawable.profile1,
                135996,
                R.drawable.post20,
                "Ballerina be like"
        );
        items.add(news20);
        return items;
    }

    public void removeLike(News news){
        mainAdapter.removeLike(news);
    }
}
