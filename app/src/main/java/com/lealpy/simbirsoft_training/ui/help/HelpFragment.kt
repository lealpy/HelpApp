package com.lealpy.simbirsoft_training.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentHelpBinding

class HelpFragment : Fragment(R.layout.fragment_help) {

    private lateinit var binding : FragmentHelpBinding

    private val viewModel : HelpViewModel by activityViewModels()

    private val helpAdapter = HelpItemAdapter(
        object: HelpItemAdapter.OnItemClickListener {
            override fun onItemClick(helpItem: HelpItem) {
                // Задел на будущее
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHelpBinding.bind(view)

        initViews()
        initObservers()

    }

    private fun initObservers() {
        viewModel.helpItems.observe(viewLifecycleOwner) { helpItems ->
            helpAdapter.submitList(helpItems)
        }
    }

    private fun initViews() {
        binding.recyclerView.adapter = helpAdapter
        val helpItemDecoration = activity?.resources?.getDimension(R.dimen.dimen_8_dp)?.let { space ->
            HelpItemDecoration(SPAN_COUNT, space.toInt())
        }

        if(helpItemDecoration != null) {
            binding.recyclerView.addItemDecoration(helpItemDecoration)
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}
