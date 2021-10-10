package com.example.foody.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*
import android.animation.ValueAnimator
import androidx.core.animation.addListener


fun calculateNoOfColumns(
    context: Context,
    columnWidthDp: Float
): Int { // For example columnWidthdp=180
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 for correct rounding to int.
}

fun View.fadeIn(){
    this.visibility = View.VISIBLE
    val fadeAnim: ObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
    fadeAnim.duration = 250
    fadeAnim.start()
}