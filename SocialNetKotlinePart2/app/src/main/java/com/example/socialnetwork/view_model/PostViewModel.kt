package com.example.socialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialnetwork.model.PostData
import com.example.socialnetwork.model.SetPostData

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val _liveData = MutableLiveData<State>()
    val liveData: LiveData<State>
        get() = _liveData

    private val postList: ArrayList<PostData> = ArrayList(
        listOf(
            SetPostData.firstPostData,
            SetPostData.secondPostData, SetPostData.thirdPostData, SetPostData.fourthPostData,
            SetPostData.fifthPostData, SetPostData.sixthPostData, SetPostData.seventhPostData,
            SetPostData.eighthPostData, SetPostData.ninthPostData, SetPostData.tenthPostData
        )
    )

    fun loadPost() {
        _liveData.value =
            State.Result(
                postList
            )
    }

    fun searchPost(postId: Int) {
        postList.forEach {
            if (postId == it.postId) {
                _liveData.value =
                    State.SearchResult(
                        it
                    )
                return
            }
        }
    }

    sealed class State {
        data class Result(val postList: ArrayList<PostData>) : State()
        data class SearchResult(val post: PostData) : State()
    }
}