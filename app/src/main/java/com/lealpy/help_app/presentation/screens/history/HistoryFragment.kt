package com.lealpy.help_app.presentation.screens.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var binding: FragmentHistoryBinding

    private val viewModel: HistoryViewModel by viewModels()

    private val adapter = HistoryItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter

        val historyItemDecoration = HistoryItemDecoration(
            requireContext().resources.getDimension(R.dimen.dimen_8_dp).toInt()
        )

        binding.recyclerView.addItemDecoration(historyItemDecoration)
    }

    private fun initObservers() {
        viewModel.donationHistoryItems.observe(viewLifecycleOwner) { donationHistoryItems ->
            adapter.submitList(donationHistoryItems)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

}
