package com.lealpy.help_app.presentation.screens.search

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentSearchBinding
import com.lealpy.help_app.presentation.screens.search.search_by_events.SearchByEventsViewModel
import com.lealpy.help_app.presentation.screens.search.search_by_nko.SearchByNkoViewModel
import com.lealpy.help_app.presentation.utils.Const.SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS
import com.lealpy.help_app.presentation.utils.Const.SEARCH_FEATURE_POSITION_SEARCH_BY_NKO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    private val searchByNkoViewModel: SearchByNkoViewModel by activityViewModels()

    private val searchByEventsViewModel: SearchByEventsViewModel by activityViewModels()

    private var searchView: SearchView? = null
    private var searchText: String? = null
    private var searchItem: MenuItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        initToolbar()
        initViewPager()
        initObservers()
        if (savedInstanceState != null) {
            searchText = savedInstanceState.getString(SEARCH_KEY)
        }
    }

    private fun initObservers() {
        searchByEventsViewModel.searchViewQuery.observe(viewLifecycleOwner) { query ->
            if (query.isNotBlank()) {
                searchItem?.expandActionView()
            }
            searchView?.setQuery(query, true)
            searchView?.clearFocus()
        }

        searchByNkoViewModel.searchViewQuery.observe(viewLifecycleOwner) { query ->
            if (query.isNotBlank()) {
                searchItem?.expandActionView()
            }
            searchView?.setQuery(query, true)
            searchView?.clearFocus()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        searchText = searchView?.query.toString()
        outState.putString(SEARCH_KEY, searchText)
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            when (position) {
                SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS -> tab.text =
                    requireActivity().getString(R.string.search_by_events_title)
                SEARCH_FEATURE_POSITION_SEARCH_BY_NKO -> tab.text =
                    requireActivity().getString(R.string.search_by_nko_title)
            }

        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS -> searchByEventsViewModel.onEventTabSelected()
                    SEARCH_FEATURE_POSITION_SEARCH_BY_NKO -> searchByNkoViewModel.onNkoTabSelected()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_toolbar_menu, menu)
        searchItem = menu.findItem(R.id.SearchToolbarSearch)
        searchView = searchItem?.actionView as SearchView?

        if (searchText != null) {
            searchItem?.expandActionView()
            searchView?.setQuery(searchText, true)
            searchView?.clearFocus()
        }

        searchView?.background = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.background_search_view
        )
        searchView?.queryHint = requireContext().getString(R.string.search_search_view_hint)
        searchView?.maxWidth = Integer.MAX_VALUE
        searchItem?.icon?.setColorFilter(requireContext().getColor(R.color.white),
            PorterDuff.Mode.SRC_ATOP)
        searchView?.setIconifiedByDefault(false)

        Observable.create<String> { emitter ->

            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(searchText: String): Boolean {
                    if (!emitter.isDisposed) {
                        emitter.onNext(searchText)
                    }
                    return false
                }

            })
        }
            .debounce(SEARCH_TIMEOUT, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.computation())
            .distinctUntilChanged() // Поиск не будет обновляться, если добавить символ, а затем удалить его в течение SEARCH_TIMEOUT
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchText ->
                    when (binding.tabLayout.selectedTabPosition) {
                        SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS -> searchByEventsViewModel.onSearchChanged(
                            searchText)
                        SEARCH_FEATURE_POSITION_SEARCH_BY_NKO -> searchByNkoViewModel.onSearchChanged(
                            searchText)
                    }
                },
                { error ->
                    throw Exception(error.message)
                },
            )

        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        private const val SEARCH_TIMEOUT: Long = 500
        private const val SEARCH_KEY = "SEARCH_KEY"
    }

}
