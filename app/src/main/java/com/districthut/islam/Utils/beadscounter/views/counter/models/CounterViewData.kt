package com.districthut.islam.Utils.beadscounter.views.counter.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


private const val DEFAULT_BEADS_COUNT = 33

@Parcelize
data class CounterViewData(
    val beadsCount: Int = DEFAULT_BEADS_COUNT,
    val beadsList: List<Bead> = emptyList(),
    val pointList: List<Point> = emptyList(),
) : Parcelable