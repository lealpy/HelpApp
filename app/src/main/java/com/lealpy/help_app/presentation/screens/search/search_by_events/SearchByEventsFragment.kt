package com.lealpy.help_app.presentation.screens.search.search_by_events

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentSearchByEventsBinding

class SearchByEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private lateinit var binding: FragmentSearchByEventsBinding

    private val viewModel: SearchByEventsViewModel by activityViewModels()

    private val eventAdapter = EventItemAdapter {
        viewModel.onItemClicked()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByEventsBinding.bind(view)
        initViews()
        initObservers()
        initSpannableString()
    }

    private fun initViews() {
        binding.recyclerView.adapter = eventAdapter

        val eventItemDivider = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.recycler_view_divider
        )?.let { drawable ->
            EventItemDecoration(
                drawable,
                requireContext().resources.getDimension(R.dimen.dimen_20_dp).toInt()
            )
        }

        if (eventItemDivider != null) {
            binding.recyclerView.addItemDecoration(eventItemDivider)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshSwiped()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.blankSearchImageVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.blankSearchImage.visibility = visibility
            binding.blankSearchHint.visibility = visibility
            binding.blankSearchExample.visibility = visibility
            binding.blankSearchExample1.visibility = visibility
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.progressBar.visibility = visibility
        }

        viewModel.nothingFoundViewsVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.nothingFound.visibility = visibility
        }

        viewModel.eventItems.observe(viewLifecycleOwner) { eventItems ->
            eventAdapter.submitList(eventItems)
        }
    }

    private fun initSpannableString() {
        val searchExample =
            SpannableStringBuilder(requireContext().getString(R.string.search_by_events_search_example_1))
        val spanStart = 0
        val spanFinish = searchExample.length

        searchExample.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                }

                override fun onClick(widget: View) {
                    viewModel.onSearchExampleClicked(searchExample.toString())
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.blankSearchExample1.text = searchExample
        binding.blankSearchExample1.movementMethod = LinkMovementMethod.getInstance()
    }

}
