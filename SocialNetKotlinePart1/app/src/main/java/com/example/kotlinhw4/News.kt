package com.example.kotlinhw4

import android.os.Parcel
import android.os.Parcelable


class News : Parcelable {
    private var name: String?
    private var date: String?
    private var text: String?
    private var likesCount: Int
    private var commentsCount: Int
    private var repostsCount: Int
    private var viewsCount: Int
    private var img: Int

    constructor(
        name: String?,
        date: String?,
        text: String?,
        likesCount: Int,
        commentsCount: Int,
        repostsCount: Int,
        viewsCount: Int,
        img: Int
    ) {
        this.name = name
        this.date = date
        this.text = text
        this.likesCount = likesCount
        this.commentsCount = commentsCount
        this.repostsCount = repostsCount
        this.viewsCount = viewsCount
        this.img = img
    }

    fun getImg(): Int {
        return img
    }

    fun setImg(img: Int) {
        this.img = img
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String?) {
        this.text = text
    }

    fun getLikesCount(): Int {
        return likesCount
    }

    fun setLikesCount(likesCount: Int) {
        this.likesCount = likesCount
    }

    fun getCommentsCount(): Int {
        return commentsCount
    }

    fun setCommentsCount(commentsCount: Int) {
        this.commentsCount = commentsCount
    }

    fun getRepostsCount(): Int {
        return repostsCount
    }

    fun setRepostsCount(repostsCount: Int) {
        this.repostsCount = repostsCount
    }

    fun getViewsCount(): Int {
        return viewsCount
    }

    fun setViewsCount(viewsCount: Int) {
        this.viewsCount = viewsCount
    }

    override fun toString(): String {
        return "News{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", repostsCount=" + repostsCount +
                ", viewsCount=" + viewsCount +  //", image" + img +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(date)
        parcel.writeString(name)
        parcel.writeString(text)
        parcel.writeInt(commentsCount)
        parcel.writeInt(likesCount)
        parcel.writeInt(repostsCount)
        parcel.writeInt(viewsCount)
        parcel.writeInt(img)
    }

    protected constructor(`in`: Parcel) {
        date = `in`.readString()
        name = `in`.readString()
        text = `in`.readString()
        commentsCount = `in`.readInt()
        likesCount = `in`.readInt()
        repostsCount = `in`.readInt()
        viewsCount = `in`.readInt()
        img = `in`.readInt()
    }

    companion object {
        val CREATOR: Parcelable.Creator<News?> = object : Parcelable.Creator<News?> {
            override fun createFromParcel(parcel: Parcel): News? {
                return News(parcel)
            }

            override fun newArray(i: Int): Array<News?> {
                return arrayOfNulls(i)
            }
        }
    }
}
