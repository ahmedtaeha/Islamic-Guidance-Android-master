package com.districthut.islam.Utils.beadscounter.views.counter

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.districthut.islam.Utils.beadscounter.views.counter.managers.AnimationManager
import com.districthut.islam.Utils.beadscounter.views.counter.managers.DrawingBeadsManager
import com.districthut.islam.Utils.beadscounter.views.counter.managers.DrawingThreadManager
import com.districthut.islam.Utils.beadscounter.views.counter.managers.beadCenterCoordinatesHelper
import com.districthut.islam.Utils.beadscounter.views.counter.models.CounterViewData
import com.google.gson.Gson
import com.mirfatif.noorulhuda.R
import kotlinx.android.synthetic.main.activity_main.view.*

private const val DEFAULT_BEADS_COUNT = 33
private const val PREFERENCE_COUNTER_VIEW  = "com.nermin.beadscounter.preferences.beads"
private const val PREFERENCE_BEADS_COUNT  = "com.nermin.beadscounter.preferences.beads.beads_count"
private const val EXTRA_COUNTER_VIEW_DATA =
    "com.nermin.beadscounter.preferences.extras.counter_view_data"

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), AnimationManager.AnimationListener {

    private val threadManager = DrawingThreadManager(context)
    private val beadsManager = DrawingBeadsManager(context)
    private val animationManager = AnimationManager(
        context, beadsManager, this
    )

    var actionsListener: CounterViewActionsListener? = null

    private var beadsCount: Int = -1

    init {
        attrs.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CounterView, 0, 0
            )
            beadsManager.beadsColor = typedArray.getColor(
                R.styleable.CounterView_beadsColor,
                ContextCompat.getColor(context, R.color.bead_color)
            )
            threadManager.threadColor = typedArray.getColor(
                R.styleable.CounterView_threadColor,
                ContextCompat.getColor(context, R.color.thread_color)
            )
            threadManager.threadThickness = typedArray.getDimension(
                R.styleable.CounterView_threadThickness,
                context.dpToPx(1).toFloat()
            ).toDouble()
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val resolvedHeight = resolveSize(heightSize, heightMeasureSpec)
        val resolvedWidth = resolveSize(widthSize, widthMeasureSpec)
        setMeasuredDimension(resolvedWidth, resolvedHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        threadManager.setMeasurements(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        threadManager.draw(canvas)
        if (hasNewBeadCenterCoordinates()) beadsManager.refreshBeadsList(true)
        beadsManager.draw(canvas)
        if (beadsCount == -1) {
            setInitialData(context.getPreferenceCounterViewData())
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            performClick()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        animationManager.onMoveNextBead()
        val doneBeads = beadsManager.doneBeadsCount
        actionsListener?.onUpdateDoneBeads(doneBeads)
        if (doneBeads == beadsCount) {
            actionsListener?.onFinish()
        }
        return true
    }

    fun reset(number: Int = beadsCount) {
        beadsCount = number
        actionsListener?.onUpdateTotalBeads(beadsCount)
        actionsListener?.onUpdateDoneBeads(0)
        beadsManager.let {
            it.beadsCount = beadsCount
            it.refreshBeadsList()
        }
        invalidate()
    }

    override fun invalidateView() {
        invalidate()
    }

    private fun getData() =
        CounterViewData(beadsCount, beadsManager.beadsList, beadsManager.pointList)

    private fun setInitialData(counterViewData: CounterViewData) {
        counterViewData.let {
            reset(it.beadsCount)
            beadsManager.setInitialDoneBeads(it.beadsList)
            actionsListener?.onUpdateDoneBeads(beadsManager.doneBeadsCount)
            invalidate()
        }
    }

    fun onStop() {
        context.setPreferenceCounterViewData(getData())
    }

    fun clear() {
        animationManager.clear()
    }

    private fun hasNewBeadCenterCoordinates(): Boolean {
        var result: Boolean
        with(beadCenterCoordinatesHelper) {
            result =
                radius != threadManager.radius ||
                        mainXCenter != threadManager.mainXCenter ||
                        mainYCenter != threadManager.mainYCenter
            radius = threadManager.radius
            mainXCenter = threadManager.mainXCenter
            mainYCenter = threadManager.mainYCenter
        }
        return result
    }
}

fun Context.dpToPx(dp: Int): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
        .toInt()

fun Context.dpToPx(dp: Double): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)

fun Context.getPreferenceCounterViewData(): CounterViewData =
    getSharedPreferences(PREFERENCE_COUNTER_VIEW, Context.MODE_PRIVATE)
        .getString(EXTRA_COUNTER_VIEW_DATA, null)?.let {
            Gson().fromJson(it, CounterViewData::class.java)
        } ?: CounterViewData()


fun Context.setPreferenceCounterViewData(data: CounterViewData) =
    getSharedPreferences(PREFERENCE_COUNTER_VIEW, Context.MODE_PRIVATE)
        .edit()
        .putString(EXTRA_COUNTER_VIEW_DATA, Gson().toJson(data))
        .commit()