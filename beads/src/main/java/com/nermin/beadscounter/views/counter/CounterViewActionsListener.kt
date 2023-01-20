package com.nermin.beadscounter.views.counter

interface CounterViewActionsListener {
    fun onUpdateTotalBeads(number: Int)
    fun onUpdateDoneBeads(number: Int)
    fun onFinish()
}