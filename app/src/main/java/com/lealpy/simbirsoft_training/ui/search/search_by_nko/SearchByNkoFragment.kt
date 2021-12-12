package com.lealpy.simbirsoft_training.ui.search.search_by_nko

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentSearchByNkoBinding

class SearchByNkoFragment : Fragment(R.layout.fragment_search_by_nko) {

    private lateinit var binding : FragmentSearchByNkoBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(SearchByNkoViewModel::class.java)
    }

    private val nkoAdapter = NkoItemAdapter(
        object: NkoItemAdapter.OnItemClickListener {
            override fun onItemClick(nkoItem: NkoItem) {
                // Задел на будущее
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchByNkoBinding.bind(view)

        initViews()
        initObservers()

    }

    private fun initObservers() {
        viewModel.nkoItems.observe(viewLifecycleOwner) { nkoItems ->
            nkoAdapter.submitList(nkoItems)
        }
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = nkoAdapter
    }

}
