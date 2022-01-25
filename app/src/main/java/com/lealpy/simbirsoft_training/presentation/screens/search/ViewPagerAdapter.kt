package com.lealpy.simbirsoft_training.presentation.screens.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lealpy.simbirsoft_training.presentation.screens.search.search_by_events.SearchByEventsFragment
import com.lealpy.simbirsoft_training.presentation.screens.search.search_by_nko.SearchByNkoFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return TABS_NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SearchByEventsFragment()
            1 -> SearchByNkoFragment()
            else -> Fragment()
        }
    }

    companion object {
        private const val TABS_NUMBER = 2
    }

}