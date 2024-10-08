package com.nermin.beadscounter.views.counter.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounterViewData(
    val beadsCount: Int,
    val beadsList: List<Bead>,
    val pointList: List<Point>,
) : Parcelable