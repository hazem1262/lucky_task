package com.example.mvvmstarterproject.utils

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import com.example.mvvmstarterproject.R
import com.tapadoo.alerter.Alert
import com.tapadoo.alerter.Alerter

object MessageUtils {
    fun showMessage(
        activity: Activity,
        title: String,
        message: String,
        @ColorRes backgroundColorRes: Int?,
        @StyleRes textAppearance: Int?
    ): Alert? {
        val alerter = Alerter.create(activity)
            .setTitle(title)
            .setText(message)
        backgroundColorRes?.let { alerter.setBackgroundColorRes(it) }
        textAppearance?.let { alerter.setTextAppearance(it) }

        return alerter.show()
    }

    fun showErrorMessage(activity: Activity, message: String): Alert? {
        return showMessage(
            activity = activity,
            title = activity.getString(R.string.error),
            message = message,
            backgroundColorRes = R.color.redOrange,
            textAppearance = R.style.AlerterTextAppearance
        )
    }

    fun showSuccessMessage(activity: Activity, message: String): Alert? {
        return showMessage(
            activity = activity,
            title = activity.getString(R.string.success),
            message = message,
            backgroundColorRes = R.color.limeade,
            textAppearance = R.style.AlerterTextAppearance
        )
    }
}