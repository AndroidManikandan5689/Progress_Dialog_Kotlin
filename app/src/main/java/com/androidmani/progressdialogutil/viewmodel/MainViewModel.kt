package com.androidmani.progressdialogutil.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val isLoading = ObservableField<Boolean>()

    init {
        isLoading.set(false)
    }

}