package com.lealpy.simbirsoft_training.ui.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentNewsFilterBinding

class NewsFilterFragment : Fragment(R.layout.fragment_news_filter) {

    private lateinit var binding : FragmentNewsFilterBinding

    private val viewModel : NewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsFilterBinding.bind(view)

        initToolbar()
        initObservers()
    }

    private fun initObservers() {
        viewModel.isChildrenChecked.observe(viewLifecycleOwner) { isChildrenChecked ->
            binding.filterSwitcherChildren.isChecked = isChildrenChecked
        }

        viewModel.isAdultsChecked.observe(viewLifecycleOwner) { isAdultsChecked ->
            binding.filterSwitcherAdults.isChecked = isAdultsChecked
        }

        viewModel.isElderlyChecked.observe(viewLifecycleOwner) { isElderlyChecked ->
            binding.filterSwitcherElderly.isChecked = isElderlyChecked
        }

        viewModel.isAnimalsChecked.observe(viewLifecycleOwner) { isAnimalsChecked ->
            binding.filterSwitcherAnimals.isChecked = isAnimalsChecked
        }

        viewModel.isEventsChecked.observe(viewLifecycleOwner) { isEventsChecked ->
            binding.filterSwitcherEvents.isChecked = isEventsChecked
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true);
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_filter_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newsFilterToolbarOk -> {
                requireActivity().onBackPressed()
                viewModel.applyFilters(
                    binding.filterSwitcherChildren.isChecked,
                    binding.filterSwitcherAdults.isChecked,
                    binding.filterSwitcherElderly.isChecked,
                    binding.filterSwitcherAnimals.isChecked,
                    binding.filterSwitcherEvents.isChecked
                )
            }
            android.R.id.home -> {
                requireActivity().onBackPressed()
            }
        }
        return true
    }

}