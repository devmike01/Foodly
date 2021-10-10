package com.example.foody.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


fun calculateNoOfColumns(
    context: Context,
    columnWidthDp: Float
): Int { // For example columnWidthdp=180
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 for correct rounding to int.
}
