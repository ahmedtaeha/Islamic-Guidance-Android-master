package com.nermin.beadscounter

import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nermin.beadscounter.databinding.ActivityMainBinding
import com.nermin.beadscounter.views.counter.CounterViewActionsListener
import com.nermin.beadscounter.views.counter.models.CounterViewData

private const val EXTRA_COUNTER_VIEW = "com.nermin.beadscounter.counter_view_data"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCounterViewListener()
        setupEditBeadsButton()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreUIState(savedInstanceState)
    }

    private fun setupCounterViewListener() {
        with(binding) {
            counterView.actionsListener = object : CounterViewActionsListener {

                override fun onUpdateTotalBeads(number: Int) {
                    totalBeadsTv.text = getString(R.string.total_beads_count, number)
                }

                override fun onUpdateDoneBeads(number: Int) {
                    doneBeadsTv.text = number.toString()
                }

                override fun onFinish() {
                    displayRestartDialog { binding.counterView.reset() }
                }
            }
        }
    }

    private fun setupEditBeadsButton() {
        binding.editIcon.setOnClickListener {
            displayBeadsCountDialog { count ->
                binding.counterView.reset(count)
            }
        }
    }

    private fun restoreUIState(bundle: Bundle?) {
        bundle?.getParcelable<CounterViewData>(EXTRA_COUNTER_VIEW)?.let {
            binding.counterView.setInitialData(it)
        }
    }

    private fun displayBeadsCountDialog(action: (Int) -> Unit) {
        dialog?.dismiss()
        dialog = AlertDialog.Builder(this).apply {
            val view = NumberPicker(this@MainActivity).also {
                it.maxValue = 99
                it.minValue = 1
            }
            setView(view)
            setCancelable(true)
            setTitle(R.string.dialog_bead_count_title)
            setPositiveButton(android.R.string.ok) { d, _ ->
                action.invoke(view.value)
                d.dismiss()
            }
            setNegativeButton(android.R.string.cancel) { d, _ ->
                d.dismiss()
            }
        }.show()
    }

    private fun displayRestartDialog(action: () -> Unit) {
        dialog?.dismiss()
        dialog = AlertDialog.Builder(this).apply {
            setCancelable(false)
            setTitle(R.string.dialog_restart_title)
            setMessage(R.string.dialog_restart_message)
            setPositiveButton(android.R.string.ok) { d, _ ->
                action.invoke()
                d.dismiss()
            }
            setNegativeButton(android.R.string.cancel) { d, _ ->
                d.dismiss()
            }
        }.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_COUNTER_VIEW, binding.counterView.getData())
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
        binding.counterView.clear()
    }
}