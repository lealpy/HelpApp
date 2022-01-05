package com.lealpy.simbirsoft_training.ui.news.news_description

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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentNewsDescriptionBinding
import com.lealpy.simbirsoft_training.ui.news.DataForNewsDescription

class NewsDescriptionFragment : Fragment(R.layout.fragment_news_description) {

    private lateinit var binding : FragmentNewsDescriptionBinding

    private val viewModel : NewsDescriptionViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsDescriptionBinding.bind(view)

        initToolbar()
        getArgumentsData()
        initViews()
        initSpanFeedback()
        initSpanSite()
        initObservers()

    }

    private fun initObservers() {

        viewModel.toastText.observe(viewLifecycleOwner) { text ->
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
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

        val appCompatActivity = (activity as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initSpanFeedback() {

        val spanFeedback = SpannableStringBuilder(activity?.getString(R.string.news_description_feedback_title))

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

        val spanFeedback = SpannableStringBuilder(activity?.getString(R.string.news_description_site_title))

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

    private fun getArgumentsData() {

        val args = arguments?.getParcelable<DataForNewsDescription>(ARGS_KEY)

        args?.title?.let { title ->
            binding.toolbarTitle.text = title
            binding.title.text = title
        }

        args?.date?.let { date ->
            binding.date.text = date
        }

        args?.fundName?.let { fundName ->
            binding.fundName.text = fundName
        }

        args?.address?.let { address ->
            binding.address.text = address
        }

        args?.phone?.let { phone ->
            binding.phone.text = phone
        }

        args?.fullText?.let { fullText ->
            binding.fullText.text = fullText
        }

        args?.image?.let { imageId ->
            binding.image.setImageResource(imageId)
        }

        args?.image2?.let { image2Id ->
            binding.image2.setImageResource(image2Id)
        }

        args?.image3?.let { image3Id ->
            binding.image3.setImageResource(image3Id)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_description_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newsDescriptionToolbarShare -> viewModel.onShareClicked()
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return true
    }

    companion object {
        const val ARGS_KEY = "ARGS_KEY"
        private const val SYMBOLS_AFTER_QUESTION = 2
    }

}
