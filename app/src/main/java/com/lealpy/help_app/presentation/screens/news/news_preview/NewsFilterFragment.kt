package com.lealpy.help_app.presentation.screens.news.news_preview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentNewsFilterBinding

class NewsFilterFragment : Fragment(R.layout.fragment_news_filter) {

    private lateinit var binding : FragmentNewsFilterBinding

    private val viewModel : NewsPreviewViewModel by activityViewModels()

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

        viewModel.isAdultChecked.observe(viewLifecycleOwner) { isAdultChecked ->
            binding.filterSwitcherAdult.isChecked = isAdultChecked
        }

        viewModel.isElderlyChecked.observe(viewLifecycleOwner) { isElderlyChecked ->
            binding.filterSwitcherElderly.isChecked = isElderlyChecked
        }

        viewModel.isAnimalChecked.observe(viewLifecycleOwner) { isAnimalChecked ->
            binding.filterSwitcherAnimal.isChecked = isAnimalChecked
        }

        viewModel.isEventChecked.observe(viewLifecycleOwner) { isEventChecked ->
            binding.filterSwitcherEvent.isChecked = isEventChecked
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_filter_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newsFilterToolbarOk -> {
                viewModel.applyFilters(
                    binding.filterSwitcherChildren.isChecked,
                    binding.filterSwitcherAdult.isChecked,
                    binding.filterSwitcherElderly.isChecked,
                    binding.filterSwitcherAnimal.isChecked,
                    binding.filterSwitcherEvent.isChecked
                )
                findNavController().popBackStack()
            }
            android.R.id.home -> {
                findNavController().popBackStack()
            }
        }
        return true
    }

}