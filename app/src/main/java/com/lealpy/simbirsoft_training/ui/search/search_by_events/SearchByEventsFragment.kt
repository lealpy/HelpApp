package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentSearchByEventsBinding

class SearchByEventsFragment : Fragment(R.layout.fragment_search_by_events) {

    private lateinit var binding : FragmentSearchByEventsBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(SearchByEventsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchByEventsBinding.bind(view)

        initObservers()

    }

    private fun initObservers() {
        viewModel.searchExampleText.observe(viewLifecycleOwner) { searchExampleText ->
            binding.searchExample.text = searchExampleText
            binding.searchExample.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
