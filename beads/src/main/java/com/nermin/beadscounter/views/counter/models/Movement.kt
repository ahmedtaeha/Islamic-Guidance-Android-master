package com.nermin.beadscounter.views.counter.models

data class Movement(
    val fromPoint: Point,
    val toPoint: Point,
    val bead: Bead,
)