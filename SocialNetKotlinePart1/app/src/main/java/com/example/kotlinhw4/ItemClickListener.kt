package com.example.kotlinhw4

import android.view.View

interface ItemClickListener {
    fun itemClick(view: View?, position: Int)
}