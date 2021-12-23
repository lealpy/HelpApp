package com.lealpy.simbirsoft_training.ui.search

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentSearchBinding
import com.lealpy.simbirsoft_training.ui.search.search_by_nko.SearchByNkoViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel : SearchByNkoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        initToolbar()
        initViewPager()

    }

    private fun initToolbar() {
        setHasOptionsMenu(true)

        val appCompatActivity = (activity as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            when(position) {
                POSITION_SEARCH_BY_EVENTS -> tab.text = requireActivity().getString(R.string.search_by_events_title)
                POSITION_SEARCH_BY_NKO -> tab.text = requireActivity().getString(R.string.search_by_nko_title)
            }

        }.attach()

        binding.tabLayout.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    viewModel.onTabSelected(tab.position)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.SearchToolbarSearch)
        val searchView = searchItem?.actionView as SearchView

        searchView.background = activity?.getDrawable(R.drawable.background_search_view)
        searchView.queryHint = activity?.getString(R.string.search_searchview_hint)
        searchView.maxWidth = Integer.MAX_VALUE
        activity?.let { searchItem.icon.setColorFilter(it.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP) }
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("query set", false)
                searchItem.collapseActionView()
                Toast.makeText(requireContext(), "onQueryTextSubmit", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(requireContext(), "onQueryTextChange", Toast.LENGTH_SHORT).show()
                return false
            }

        })

        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.SearchToolbarSearch -> Toast.makeText(
                requireContext(),
                requireActivity().getString(R.string.search_search_btn_click_message),
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    companion object {
        const val POSITION_SEARCH_BY_EVENTS = 0
        const val POSITION_SEARCH_BY_NKO = 1
    }

}
