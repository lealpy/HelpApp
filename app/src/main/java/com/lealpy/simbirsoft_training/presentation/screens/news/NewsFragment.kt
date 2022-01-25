package com.lealpy.simbirsoft_training.presentation.screens.news

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.simbirsoft_training.presentation.MainActivity
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentNewsBinding
import com.lealpy.simbirsoft_training.presentation.screens.news.news_description.NewsDescriptionFragment

class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding : FragmentNewsBinding

    private val viewModel : NewsViewModel by activityViewModels()

    private val newsAdapter = NewsItemAdapter{ newsItem ->
        val args = Bundle()
        args.putLong(NewsDescriptionFragment.ARGS_KEY, newsItem.id)

        findNavController().navigate(
            R.id.actionNavigationNewsToNewsDescriptionFragment,
            args
        )

        viewModel.onNewsViewed(newsItem.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        initViews()
        initObservers()
        initToolbar()
        viewModel.onViewCreated()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true);

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViews() {
        binding.recyclerView.adapter = newsAdapter

        val newsItemDecoration = NewsItemDecoration(
            requireContext().resources.getDimension(R.dimen.dimen_8_dp).toInt()
        )

        binding.recyclerView.addItemDecoration(newsItemDecoration)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onSwipedRefresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.newsItemsUi.observe(viewLifecycleOwner) { newsItems ->
            newsAdapter.submitList(newsItems)
            viewModel.onNewsItemsUpdated()
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
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newsToolbarFilter -> {
                findNavController().navigate(R.id.actionNavigationNewsToNewsFilterFragment)
            }
        }
        return true
    }

}
