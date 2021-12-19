package com.lealpy.simbirsoft_training.ui.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding : FragmentNewsBinding

    private val viewModel : NewsViewModel by activityViewModels()

    private val newsAdapter = NewsItemAdapter(
        object : NewsItemAdapter.OnItemClickListener {
            override fun onItemClick(newsItem: NewsItem) {
                // Задел на будущее
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsBinding.bind(view)

        initViews()
        initObservers()
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true);
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = newsAdapter

        val newsItemDecoration = activity?.resources?.getDimension(R.dimen.dimen_8_dp)?.let { spacing ->
            NewsItemDecoration(spacing.toInt())
        }

        if(newsItemDecoration != null) {
            binding.recyclerView.addItemDecoration(newsItemDecoration)
        }

    }

    private fun initObservers() {
        viewModel.newsItems.observe(viewLifecycleOwner) { newsItems ->
            newsAdapter.submitList(newsItems)
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
                // save to VM ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            }
        }
        return true
    }

}
