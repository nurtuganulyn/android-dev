package com.codingwithmitch.kotlinrecyclerviewexample

import com.codingwithmitch.kotlinrecyclerviewexample.models.BlogPost

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<BlogPost>{
            val list = ArrayList<BlogPost>()
            list.add(
                BlogPost(
                    "Congratulations!",
                    464045,
                    R.drawable.img1,
                    "Sally"
                )
            )
            list.add(
                BlogPost(
                    "Time to Build a Kotlin App!",
                    464045,
                    R.drawable.img2,
                    "mitch"
                )
            )

            list.add(
                BlogPost(
                    "Interviewing a Web Developer and YouTuber",
                    464045,
                    R.drawable.img3,
                    "John"
                )
            )
            list.add(
                BlogPost(
                    "Freelance Android Developer (Vasiliy Zukanov)",
                    464045,
                    R.drawable.img4,
                    "Steven"
                )
            )
            list.add(
                BlogPost(
                    "Freelance Android Developer, Donn Felker",
                    464045,
                    R.drawable.img5,
                    "Richelle"
                )
            )
            list.add(
                BlogPost(
                    "Work Life Balance for Software Developers",
                    464045,
                    R.drawable.img6,
                    "Jessica"
                )
            )
            list.add(
                BlogPost(
                    "Full Stack Web Developer - Nicholas Olsen",
                    464045,
                    R.drawable.img7,
                    "Guy"
                )
            )
            list.add(
                BlogPost(
                    "Javascript Expert - Wes Bos",
                    464045,
                    R.drawable.img8,
                    "Ruby"
                )
            )
            list.add(
                BlogPost(
                    "Senior Android Engineer - Kaushik Gopal",
                    464045,
                    R.drawable.img9,
                    "mitch"
                )
            )
            return list
        }
    }
}