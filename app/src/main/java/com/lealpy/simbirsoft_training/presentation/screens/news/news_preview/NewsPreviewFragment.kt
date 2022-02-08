package com.lealpy.simbirsoft_training.presentation.screens.news.news_preview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.simbirsoft_training.presentation.MainActivity
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentNewsPreviewBinding
import com.lealpy.simbirsoft_training.presentation.screens.news.news_description.NewsDescriptionFragment

class NewsPreviewFragment : Fragment(R.layout.fragment_news_preview) {

    private lateinit var binding : FragmentNewsPreviewBinding

    private val viewModel : NewsPreviewViewModel by activityViewModels()

    private val newsAdapter = NewsPreviewItemAdapter{ newsItem ->
        val args = Bundle()
        args.putLong(NewsDescriptionFragment.ARGS_KEY, newsItem.id)

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
        setHasOptionsMenu(true);

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
