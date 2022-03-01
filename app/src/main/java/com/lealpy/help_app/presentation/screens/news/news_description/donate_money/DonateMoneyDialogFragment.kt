package com.lealpy.help_app.presentation.screens.news.news_description.donate_money

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.DialogFragmentDonateMoneyBinding
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_1_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_2_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_3_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_4_KEY
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class DonateMoneyDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentDonateMoneyBinding

    private val viewModel: DonateMoneyViewModel by viewModels()

    private lateinit var alertDialog: AlertDialog

    private val positiveButton by lazy {
        alertDialog.getButton(Dialog.BUTTON_POSITIVE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentDonateMoneyBinding.inflate(layoutInflater)
        alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton(R.string.dialog_fragment_donate_money_positive_button) { _, _ ->
                viewModel.onPositiveButtonClicked(binding.donationEditText.text)
            }
            .setNegativeButton(R.string.dialog_fragment_donate_money_negative_button, null)
            .create()
        return alertDialog
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.onSaveInstanceState(binding.donationEditText.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun initViews() {
        binding.donationOffer.addOnButtonCheckedListener { _, checkedId, isChecked ->
            viewModel.onDonationOfferChecked(checkedId, isChecked)
        }

        val textChangedDisposable = Observable.create<String> { emitter ->
            binding.donationEditText.addTextChangedListener { donationAmount ->
                if (!emitter.isDisposed) {
                    emitter.onNext(donationAmount.toString())
                }
            }
        }

        viewModel.getTextChangedDisposable(textChangedDisposable)
    }

    fun initObservers() {
        viewModel.donationText.observe(this) { text ->
            binding.donationEditText.setText(text)
            binding.donationEditText.setSelection(binding.donationEditText.text.length)
        }

        viewModel.selectedButton.observe(this) { key ->
            binding.donationOffer.clearChecked()
            binding.donationOffer1.isChecked = DONATE_MONEY_FEATURE_DONATION_OFFER_1_KEY == key
            binding.donationOffer2.isChecked = DONATE_MONEY_FEATURE_DONATION_OFFER_2_KEY == key
            binding.donationOffer3.isChecked = DONATE_MONEY_FEATURE_DONATION_OFFER_3_KEY == key
            binding.donationOffer4.isChecked = DONATE_MONEY_FEATURE_DONATION_OFFER_4_KEY == key
        }

        viewModel.isPositiveButtonEnabled.observe(this) { isEnabled ->
            positiveButton.isEnabled = isEnabled
        }
    }

}
