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
import com.lealpy.help_app.databinding.FragmentNewsPreviewBinding
import com.lealpy.help_app.presentation.MainActivity
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY

class NewsPreviewFragment : Fragment(R.layout.fragment_news_preview) {

    private lateinit var binding: FragmentNewsPreviewBinding

    private val viewModel: NewsPreviewViewModel by activityViewModels()

    private val newsAdapter = NewsPreviewItemAdapter { newsItem ->
        val args = Bundle()
        args.putLong(NEWS_FEATURE_NEWS_ID_KEY, newsItem.id)

        findNavController().navigate(
            R.id.actionNavigationNewsToNewsDescriptionFragment,
            args
        )

        viewModel.onNewsWatched(newsItem.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsPreviewBinding.bind(view)
        initViews()
        initObservers()
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViews() {
        binding.recyclerView.adapter = newsAdapter

        val newsItemDecoration = NewsPreviewItemDecoration(
            requireContext().resources.getDimension(R.dimen.dimen_8_dp).toInt()
        )

        binding.recyclerView.addItemDecoration(newsItemDecoration)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onSwipedRefresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.newsPreviewItemsUi.observe(viewLifecycleOwner) { newsItems ->
            newsAdapter.submitList(newsItems)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }

        viewModel.badgeNumber.observe(viewLifecycleOwner) { badgeNumber ->
            (requireActivity() as? MainActivity)?.badgeSubject?.onNext(badgeNumber)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.newsToolbarFilter -> {
                findNavController().navigate(R.id.actionNavigationNewsToNewsFilterFragment)
            }
        }
        return true
    }

}
