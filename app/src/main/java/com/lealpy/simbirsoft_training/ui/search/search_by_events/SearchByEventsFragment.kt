package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentSearchByEventsBinding

class SearchByEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private lateinit var binding : FragmentSearchByEventsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchByEventsBinding.bind(view)

        initSpannableString()

    }

    private fun initSpannableString() {

        val searchExample = SpannableStringBuilder(activity?.getString(R.string.search_by_events_search_example))

        val spanStart = searchExample.indexOf(' ') + 1
        val spanFinish = searchExample.length

        searchExample.setSpan(
            activity?.getColor(R.color.leaf)?.let { ForegroundColorSpan(it) },
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
                        requireContext(),
                        activity?.getString(R.string.click_heard),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.searchExample.text = searchExample
        binding.searchExample.movementMethod = LinkMovementMethod.getInstance()

    }
}
