package com.lealpy.help_app.presentation.screens.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lealpy.help_app.presentation.screens.search.search_by_events.SearchByEventsFragment
import com.lealpy.help_app.presentation.screens.search.search_by_nko.SearchByNkoFragment
import com.lealpy.help_app.presentation.utils.Const.SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS
import com.lealpy.help_app.presentation.utils.Const.SEARCH_FEATURE_POSITION_SEARCH_BY_NKO

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return TABS_NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            SEARCH_FEATURE_POSITION_SEARCH_BY_EVENTS -> SearchByEventsFragment()
            SEARCH_FEATURE_POSITION_SEARCH_BY_NKO -> SearchByNkoFragment()
            else -> Fragment()
        }
    }

    companion object {
        private const val TABS_NUMBER = 2
    }

}
