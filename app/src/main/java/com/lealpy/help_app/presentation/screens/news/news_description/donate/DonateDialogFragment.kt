package com.lealpy.help_app.presentation.screens.news.news_description.donate

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.DialogFragmentDonateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonateDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentDonateBinding

    private lateinit var alertDialog: AlertDialog

    private val viewModel: DonateViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentDonateBinding.inflate(layoutInflater)
        alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton(R.string.dialog_fragment_donate_positive_button, null)
            .create()
        return alertDialog
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initObservers()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        viewModel.dialogText.observe(this) { dialogText ->
            binding.dialogText.text = dialogText
        }
    }

}
