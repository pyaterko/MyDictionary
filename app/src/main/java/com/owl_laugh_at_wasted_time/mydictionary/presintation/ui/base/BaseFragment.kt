package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.mydictionary.R
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.activity.MainActivity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import javax.inject.Inject


open class BaseFragment(layout: Int) : Fragment(layout) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (activity as MainActivity).component
    }

     fun setupSwipe(recyclerView: RecyclerView,block:( viewHolder: RecyclerView.ViewHolder) ->Unit) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
              block.invoke(viewHolder)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun displayAConfirmationDialog(
        context: Context,
        title: String,
        actionPB1: (() -> Unit)? = null,
        actionNB1: (() -> Unit)? = null,
    ) {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    actionPB1?.invoke()
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    actionNB1?.invoke()
                }
            }
        }
        val titleView = TextView(context)
        titleView.text = title
        titleView.setPadding(10, 10, 10, 10)
        titleView.gravity = Gravity.CENTER
        titleView.setTextColor(Color.WHITE)
        titleView.textSize = 20f
        val dialog = android.app.AlertDialog.Builder(context)
            .setCancelable(true)
            .setCustomTitle(titleView)
            .setPositiveButton(R.string.action_yes, listener)
            .setNeutralButton(R.string.action_no, listener)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        dialog?.window?.let {
            val lp = it.attributes
            it.setGravity(Gravity.BOTTOM)
            lp.y = 200
            it.setBackgroundDrawableResource(R.drawable.backgraund_selected)
        }
    }


}

fun Fragment.launchAndRepeatOnStart(block: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block.invoke()
        }
    }
}