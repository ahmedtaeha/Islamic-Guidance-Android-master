package com.nermin.beadscounter.views.counter.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Point(
    val index: Int,
    val angle: Double,
    val xCenter: Float,
    val yCenter: Float,
) : Parcelable