package com.lealpy.simbirsoft_training.ui.help

import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentHelpBinding
import com.lealpy.simbirsoft_training.utils.AppUtils

class HelpFragment : Fragment(R.layout.fragment_help) {

    private lateinit var binding : FragmentHelpBinding

    private val progressBarVisibility = MutableLiveData<Int>()

    private val helpItems = MutableLiveData<List<HelpItem>>()

    private val helpAdapter = HelpItemAdapter(
        object: HelpItemAdapter.OnItemClickListener {
            override fun onItemClick(helpItem: HelpItem) {
                // Задел на будущее
            }
        }
    )

    private val getHelpItemsFromJSON = GetHelpItemsFromJSON()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHelpBinding.bind(view)

        initViews()
        initObservers()

        if (savedInstanceState == null) {
            getHelpItemsFromJSON.execute()
        }
        else {
            val savedHelpItems = savedInstanceState.getParcelableArrayList<HelpItem>(HELP_ITEMS_KEY)
            if(savedHelpItems != null) helpItems.value = savedHelpItems
            else getHelpItemsFromJSON.execute()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (getHelpItemsFromJSON.status != AsyncTask.Status.FINISHED)
            getHelpItemsFromJSON.cancel(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(HELP_ITEMS_KEY, helpItems.value as? ArrayList<Parcelable>)
    }

    private fun initViews() {
        binding.recyclerView.adapter = helpAdapter
        val helpItemDecoration = activity?.resources?.getDimension(R.dimen.dimen_8_dp)?.let { space ->
            HelpItemDecoration(SPAN_COUNT, space.toInt())
        }

        if(helpItemDecoration != null) {
            binding.recyclerView.addItemDecoration(helpItemDecoration)
        }
    }

    private fun initObservers() {

        helpItems.observe(viewLifecycleOwner) { helpItems ->
            helpAdapter.submitList(helpItems)
        }

        progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

    inner class GetHelpItemsFromJSON : AsyncTask<Unit, Unit, List<HelpItem>>() {

        override fun onPreExecute() {
            progressBarVisibility.value = View.VISIBLE
        }

        override fun doInBackground(vararg params: Unit?): List<HelpItem> {
            Thread.sleep(THREAD_SLEEP_MILLIS)
            val jsonFileString = AppUtils.getJsonDataFromAsset(requireContext(), HELP_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<HelpItem>>() {}.type
            return gson.fromJson(jsonFileString, itemTypes)
        }

        override fun onPostExecute(helpItemsResult: List<HelpItem>?) {
            helpItems.value = helpItemsResult
            progressBarVisibility.value = View.INVISIBLE
        }

    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
        private const val THREAD_SLEEP_MILLIS : Long = 5000
        private const val HELP_ITEMS_KEY = "HELP_ITEMS_KEY"
    }

}
