package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.app.Application
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R

class SearchByEventsViewModel(application: Application) : AndroidViewModel(application) {

    private val _searchExampleText = MutableLiveData (
        initSpannableString()
    )
    val searchExampleText : LiveData<SpannableStringBuilder> = _searchExampleText

    private fun initSpannableString(): SpannableStringBuilder {

        val searchExample = SpannableStringBuilder(getApplication<Application>().getString(R.string.search_by_events_search_example))

        val spanStart = searchExample.indexOf(' ') + 1
        val spanFinish = searchExample.length

        searchExample.setSpan(
            ForegroundColorSpan(getApplication<Application>().getColor(R.color.leaf)),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        searchExample.setSpan(
            UnderlineSpan(),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        searchExample.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    Toast.makeText(
                        getApplication(),
                        getApplication<Application>().getString(R.string.click_heard),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return searchExample

    }

}
