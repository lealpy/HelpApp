package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentSearchByEventsBinding

class SearchByEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private lateinit var binding : FragmentSearchByEventsBinding

    private val viewModel : SearchByEventsViewModel by activityViewModels()

    private val eventAdapter = EventItemAdapter(
        object: EventItemAdapter.OnItemClickListener {
            override fun onItemClick(eventItem: EventItem) {
                // Задел на будущее
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByEventsBinding.bind(view)

        initViews()
        initObservers()
        initSpannableString()
        //initOnClick()
    }

    private fun initViews() {

        binding.recyclerView.adapter = eventAdapter

        val eventItemDivider =
            activity?.getDrawable(R.drawable.recycler_view_divider)?.let { drawable ->
                activity?.resources?.getDimension(R.dimen.dimen_20_dp)?.toInt()?.let { paddingLeft ->
                    EventItemDecoration(drawable, paddingLeft)
                }
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

        viewModel.recyclerViewVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.recyclerView.visibility = visibility
        }


        viewModel.eventItems.observe(viewLifecycleOwner) { eventItems ->
            eventAdapter.submitList(eventItems)
        }
    }

    private fun initSpannableString() {

        val searchExample = SpannableStringBuilder(activity?.getString(R.string.search_by_events_search_example_1))

        val spanStart = 0
        val spanFinish = searchExample.length

        searchExample.setSpan(
            object: ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                }

                override fun onClick(widget: View) {
                    viewModel.onSearchExampleClicked(searchExample.toString())
                    showToast()
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.blankSearchExample1.text = searchExample
        binding.blankSearchExample1.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun initOnClick() {
        binding.blankSearchExample1.setOnClickListener {
            showToast()
        }
    }

    private fun showToast() {
        Toast.makeText(
            requireContext(),
            activity?.getString(R.string.click_heard),
            Toast.LENGTH_SHORT
        ).show()
    }

}
