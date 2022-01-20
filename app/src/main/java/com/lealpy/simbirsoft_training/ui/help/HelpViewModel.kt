package com.lealpy.simbirsoft_training.ui.help

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference

class HelpViewModel (application: Application) : AndroidViewModel(application) {

    private val _helpItems = MutableLiveData<List<HelpItem>>()
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val asyncTaskResponse = object : AsyncTaskResponse {
        override fun preExecute() {
            _progressBarVisibility.value = View.VISIBLE
        }

        override fun postExecute(helpItemsResult: List<HelpItem>?) {
            _helpItems.value = helpItemsResult
            _progressBarVisibility.value = View.GONE
        }
    }

    fun onViewCreated(savedInstanceState : Bundle?) {
        if (savedInstanceState == null || _helpItems.value == null) {
            val getHelpItemsAsyncTask = GetHelpItemsAsyncTask(
                asyncTaskResponse,
                WeakReference(getApplication())
            )

            getHelpItemsAsyncTask.execute()
        }
    }

}
