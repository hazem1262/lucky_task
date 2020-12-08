package com.example.mvvmstarterproject.utils.network

import android.app.Activity
import android.app.Dialog
import com.example.mvvmstarterproject.R
import java.lang.ref.WeakReference

class LoadingHandler private constructor(private val activity: WeakReference<Activity>) {
    private lateinit var progressDialog: Dialog

    init {
        if (!::progressDialog.isInitialized) {
            activity.get()?.let {
                progressDialog = Dialog(
                    it,
                    R.style.ProgressDialogCustomTheme
                )
                progressDialog.setContentView(R.layout.view_progress)
                progressDialog.setCancelable(false)
            }
        }
    }

    fun showLoading() = progressDialog.show()

    fun hideLoading() = progressDialog.hide()

    fun stop(): Unit {
        if (progressDialog.isShowing)
            progressDialog.cancel()
    }

    companion object {
        fun getInstance(activity: Activity): LoadingHandler {
            return LoadingHandler(
                WeakReference(activity)
            )
        }
    }
}