package com.nermin.beadscounter.views.counter.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bead(
    var pointIndex: Int,
    var xCenter: Float,
    var yCenter: Float,
    var isDone: Boolean = false,
) : Parcelable