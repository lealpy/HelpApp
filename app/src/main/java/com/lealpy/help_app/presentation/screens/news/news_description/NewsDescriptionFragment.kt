package com.lealpy.help_app.presentation.screens.news.news_description

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentNewsDescriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDescriptionFragment : Fragment(R.layout.fragment_news_description) {

    private lateinit var binding : FragmentNewsDescriptionBinding

    private val viewModel : NewsDescriptionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDescriptionBinding.bind(view)
        initObservers()
        initViews()
        initToolbar()
        initSpanFeedback()
        initSpanSite()
    }

    private fun initObservers() {
        viewModel.newsDescriptionItemUi.observe(viewLifecycleOwner) { newsItem ->
            binding.toolbarTitle.text = newsItem.title
            binding.title.text = newsItem.title
            binding.date.text = newsItem.date
            binding.fundName.text = newsItem.fundName
            binding.address.text = newsItem.address
            binding.phone.text = newsItem.phone
            binding.fullText.text = newsItem.fullText
            binding.image.setImageBitmap(newsItem.image)
            binding.image2.setImageBitmap(newsItem.image2)
            binding.image3.setImageBitmap(newsItem.image3)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.blankScreen.visibility = visibility
            binding.progressBar.visibility = visibility
        }

        viewModel.dialogArgs.observe(viewLifecycleOwner) { args ->
            findNavController().navigate(
                R.id.actionNewsDescriptionFragmentToDonateMoneyDialogFragment,
                args
            )
        }
    }

    private fun initViews() {
        binding.site.setOnClickListener { viewModel.onSiteClicked() }
        binding.donateThings.setOnClickListener { viewModel.onDonateThingsClicked() }
        binding.becomeVolunteer.setOnClickListener { viewModel.onBecomeVolunteerClicked() }
        binding.profHelp.setOnClickListener { viewModel.onProfHelpClicked() }
        binding.donateMoney.setOnClickListener { viewModel.onDonateMoneyClicked() }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)

        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initSpanFeedback() {
        val spanFeedback = SpannableStringBuilder(requireContext().getString(R.string.news_description_feedback_title))

        val spanStart = spanFeedback.indexOf('?') + SYMBOLS_AFTER_QUESTION
        val spanFinish = spanFeedback.length

        spanFeedback.setSpan(
            UnderlineSpan(),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) { viewModel.onSpanFeedbackClicked() }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

            binding.feedback.text = spanFeedback
            binding.feedback.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initSpanSite() {
        val spanFeedback = SpannableStringBuilder(requireContext().getString(R.string.news_description_site_title))

        val spanStart = 0
        val spanFinish = spanFeedback.length

        spanFeedback.setSpan(
            UnderlineSpan(),
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanFeedback.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) { viewModel.onSpanSiteClicked() }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.site.text = spanFeedback
        binding.site.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_description_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newsDescriptionToolbarShare -> viewModel.onShareClicked()
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

    companion object {
        private const val SYMBOLS_AFTER_QUESTION = 2
    }

}
