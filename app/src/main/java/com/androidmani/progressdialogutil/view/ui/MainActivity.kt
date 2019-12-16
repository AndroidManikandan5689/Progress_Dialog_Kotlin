package com.androidmani.progressdialogutil.view.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.androidmani.progressdialogutil.R
import com.androidmani.progressdialogutil.databinding.ActivityMainBinding
import com.androidmani.progressdialogutil.utils.BaseAsyncTask
import com.androidmani.progressdialogutil.utils.ProgressDialog
import com.androidmani.progressdialogutil.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    public lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mailViewModel = viewModel

    }


    fun showLoader(v: View) {
        viewModel.isLoading.set(true)
        FakeAsyncTask(object : BaseAsyncTask.ProgressListener {
            override fun onStarted() {
                //Show Progrss Bar
                showProgress()
                viewModel.isLoading.set(true)
            }

            override fun onCompleted() {
                //Hide Progress Bar
                hideProgress()
                viewModel.isLoading.set(false)
            }

            override fun onError(errorMessage: String?) {
                //Hide Progress Bar
                hideProgress()
                viewModel.isLoading.set(false)
            }

        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

    }

    fun showProgress() {
        ProgressDialog.show(this, "Please wait 5 seconds...")
    }

    fun hideProgress() {
        ProgressDialog.hide(this)
    }

    class FakeAsyncTask(listener: ProgressListener) : BaseAsyncTask(listener) {

        override fun doInBackground(vararg params: Void?): String? {
            for (i in 1..5) {
                Thread.sleep(1000)
                Log.e("thread", "Thread $i")
            }

            return "success"
        }
    }


}
