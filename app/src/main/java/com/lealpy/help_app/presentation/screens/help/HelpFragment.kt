package com.lealpy.help_app.presentation.screens.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentHelpBinding

class HelpFragment : Fragment(R.layout.fragment_help) {

    private lateinit var binding: FragmentHelpBinding

    private val viewModel: HelpViewModel by activityViewModels()

    private val helpAdapter = HelpItemAdapter {
        viewModel.onItemClicked()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHelpBinding.bind(view)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.adapter = helpAdapter

        val helpItemDecoration = HelpItemDecoration(
            SPAN_COUNT,
            requireContext().resources.getDimension(R.dimen.dimen_8_dp).toInt()
        )

        binding.recyclerView.addItemDecoration(helpItemDecoration)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onSwipedRefresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.helpItemsUi.observe(viewLifecycleOwner) { helpItemsUi ->
            helpAdapter.submitList(helpItemsUi)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
    }

}
