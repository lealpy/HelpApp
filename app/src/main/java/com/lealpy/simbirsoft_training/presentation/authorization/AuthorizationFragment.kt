package com.lealpy.simbirsoft_training.presentation.authorization

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_authorization.*
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var binding : FragmentAuthorizationBinding

    private val viewModel : AuthorizationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)
        initViews()
        initObservers()
        initEditTextLengthWatcher()
        initSpannableStringForgotPassword()
        initSpannableStringRegistration()
        initToolbar()
    }

    private fun initViews() {
        binding.enterBtn.setOnClickListener { viewModel.onEnterBtnClicked() }
        binding.vkIcon.setOnClickListener { viewModel.onVkIconClicked() }
        binding.fbIcon.setOnClickListener { viewModel.onFbIconClicked() }
        binding.okIcon.setOnClickListener { viewModel.onOkIconClicked() }
    }

    private fun initObservers() {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateTo ->
            findNavController().navigate(navigateTo)
        }

        viewModel.isEnterBtnEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.enterBtn.isEnabled = isEnabled
        }
    }

    private fun initEditTextLengthWatcher() {
        val observable = Observable.create<Map<String, Int>> { emitter ->
            binding.emailEditText.addTextChangedListener {
                emitter.onNext(
                    mapOf(
                        EMAIL_KEY to emailEditText.length(),
                        PASSWORD_KEY to passwordEditText.length()
                    )
                )
            }

            binding.passwordEditText.addTextChangedListener {
                emitter.onNext(
                    mapOf(
                        EMAIL_KEY to emailEditText.length(),
                        PASSWORD_KEY to passwordEditText.length()
                    )
                )
            }
        }

        viewModel.onEditTextLengthWatcherInit(observable)
    }

    private fun initSpannableStringForgotPassword() {
        val forgotPasswordButton = SpannableStringBuilder(requireContext().getString(R.string.authorization_forgot_password_button))
        val spanStart = 0
        val spanFinish = forgotPasswordButton.length

        forgotPasswordButton.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    viewModel.onForgotPasswordSpanClicked()
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.forgotPasswordBtn.text = forgotPasswordButton
        binding.forgotPasswordBtn.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initSpannableStringRegistration() {
        val registrationButton = SpannableStringBuilder(requireContext().getString(R.string.authorization_registration_button))
        val spanStart = 0
        val spanFinish = registrationButton.length

        registrationButton.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    viewModel.onRegistrationSpanClicked()
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                }
            },
            spanStart,
            spanFinish,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.registrationBtn.text = registrationButton
        binding.registrationBtn.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true);
        val appCompatActivity = requireActivity() as? AppCompatActivity
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
            }
        }
        return true
    }

    companion object {
        const val EMAIL_KEY = "AUTHORIZATION_EMAIL_KEY"
        const val PASSWORD_KEY = "AUTHORIZATION_PASSWORD_KEY"
    }

}
