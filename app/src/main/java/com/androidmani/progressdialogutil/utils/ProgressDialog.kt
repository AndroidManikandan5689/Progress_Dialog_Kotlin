package com.androidmani.progressdialogutil.utils


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidmani.progressdialogutil.R

class ProgressDialog : DialogFragment() {


    private var messageString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.messageString = arguments?.getString("message")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.loading_dialog_layout, container, false)
        view.findViewById<TextView>(R.id.message).text = messageString
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false) //dismiss the dialog when outer touch if its in true
        dialog.setCancelable(false)
        //Dialog dismissed when press back icon
//        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->  if(keyCode== KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
//            dialog.dismiss()
//        }
//        return@OnKeyListener true})
        return dialog
    }


    companion object {
        const val TAG = "progress_dialog"

        fun show(activity: AppCompatActivity, message: String) {
            var fragment = activity.supportFragmentManager.findFragmentByTag(TAG)
            if (fragment == null) {
                fragment = ProgressDialog()
                fragment.arguments = Bundle().apply {
                    putString("message", message)
                }
                fragment.isCancelable = false
                activity.supportFragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitAllowingStateLoss()
            }
        }


        fun hide(activity: AppCompatActivity) {
            val fragment = activity.supportFragmentManager.findFragmentByTag(TAG)
            if (fragment != null) {
                activity.supportFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
            }
        }
    }
}
