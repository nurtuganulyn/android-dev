package com.codingwithmitch.kotlinrecyclerviewexample.models

data class BlogPost(

    var title: String,

    var likes: Int,

    var image: Int,

    var username: String // Author of blog post


) {

    override fun toString(): String {
        return "BlogPost(title='$title', image='$image', username='$username')"
    }


}
























