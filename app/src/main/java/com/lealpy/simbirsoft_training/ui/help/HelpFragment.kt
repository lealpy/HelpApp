package com.lealpy.simbirsoft_training.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
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
        binding.recyclerView.layoutManager = GridLayoutManager(context, spanCount)
        binding.recyclerView.adapter = helpAdapter
    }

    companion object {
        private const val spanCount = 2
    }
}
