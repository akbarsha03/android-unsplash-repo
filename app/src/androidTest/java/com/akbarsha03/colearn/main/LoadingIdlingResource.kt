package com.akbarsha03.colearn.main

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.IdlingResource
import com.akbarsha03.colearn.R

/**
 * An idling resource which checks which tells Espresso to wait until Progress Bar dismisses
 */
class LoadingIdlingResource constructor(private val activity: Activity) : IdlingResource {
    private var resourceCallback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return "LoadingIdlingResource"
    }

    override fun isIdleNow(): Boolean {
        return if (!isProgressShowing()) {
            resourceCallback?.onTransitionToIdle()
            true
        } else {
            false
        }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    private fun isProgressShowing(): Boolean {
        val progress = activity.findViewById<ProgressBar>(R.id.progress_bar)
        return progress.visibility == View.VISIBLE
    }
}