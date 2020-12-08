package com.lucky.task.utils.dataBinding

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("strikeThrough")
fun strikeThrough(view: TextView, show: Boolean) {
    view.paintFlags = if (show) {
        view.paintFlags or STRIKE_THRU_TEXT_FLAG
    } else {
        view.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }
}