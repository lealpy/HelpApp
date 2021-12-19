package com.lealpy.simbirsoft_training.ui.news.news_description

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

class NewsDescriptionViewModel (application: Application) : AndroidViewModel(application) {

    private val _feedbackText = MutableLiveData ( initSpanFeedback() )
    val feedbackText : LiveData<SpannableStringBuilder> = _feedbackText

    private val _siteText = MutableLiveData ( initSpanSite() )
    val siteText : LiveData<SpannableStringBuilder> = _siteText

    private fun initSpanFeedback(): SpannableStringBuilder {

        val spanFeedback = SpannableStringBuilder(getApplication<Application>().getString(R.string.news_description_feedback_title))

        val spanStart = spanFeedback.indexOf('?') + SYMBOLS_AFTER_QUESTION
        val spanFinish = spanFeedback.length

        spanFeedback.setSpan(
            ForegroundColorSpan(getApplication<Application>().getColor(R.color.leaf)),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            UnderlineSpan(),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) { showToast() }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spanFeedback

    }

    private fun initSpanSite(): SpannableStringBuilder {

        val spanFeedback = SpannableStringBuilder(getApplication<Application>().getString(R.string.news_description_site_title))

        val spanStart = 0
        val spanFinish = spanFeedback.length

        spanFeedback.setSpan(
            ForegroundColorSpan(getApplication<Application>().getColor(R.color.leaf)),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            UnderlineSpan(),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) { showToast() }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spanFeedback

    }

    private fun showToast() {
        Toast.makeText(
            getApplication(),
            getApplication<Application>().getString(R.string.click_heard),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onSiteClicked() {
        showToast()
    }

    fun onDonateThingsClicked() {
        showToast()
    }

    fun onBecomeVolunteerClicked() {
        showToast()
    }

    fun onProfHelpClicked() {
        showToast()
    }

    fun onDonateMoneyClicked() {
        showToast()
    }

    fun onShareClicked() {
        showToast()
    }

    companion object {
        const val SYMBOLS_AFTER_QUESTION = 2
    }



}