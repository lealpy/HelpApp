package com.lealpy.help_app.presentation.screens.auth.sign_in

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentSignInBinding
import com.lealpy.help_app.presentation.utils.Const.AUTHORIZATION_FEATURE_EMAIL_KEY
import com.lealpy.help_app.presentation.utils.Const.AUTHORIZATION_FEATURE_PASSWORD_KEY
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        initViews()
        initObservers()
        initEditTextLengthWatcher()
        initSpannableStringForgotPassword()
        initSpannableStringRegistration()
        initToolbar()
    }

    private fun initViews() {
        binding.loginBtn.setOnClickListener {
            viewModel.onSignInBtnClicked(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateTo ->
            findNavController().navigate(navigateTo)
        }

        viewModel.isSignInBtnEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.loginBtn.isEnabled = isEnabled
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }
    }

    private fun initEditTextLengthWatcher() {
        val observable = Observable.create<Map<String, String>> { emitter ->
            binding.emailEditText.addTextChangedListener {
                emitter.onNext(
                    mapOf(
                        AUTHORIZATION_FEATURE_EMAIL_KEY to binding.emailEditText.text.toString(),
                        AUTHORIZATION_FEATURE_PASSWORD_KEY to binding.passwordEditText.text.toString()
                    )
                )
            }

            binding.passwordEditText.addTextChangedListener {
                emitter.onNext(
                    mapOf(
                        AUTHORIZATION_FEATURE_EMAIL_KEY to binding.emailEditText.text.toString(),
                        AUTHORIZATION_FEATURE_PASSWORD_KEY to binding.passwordEditText.text.toString()
                    )
                )
            }
        }

        viewModel.onEditTextLengthWatcherInit(observable)
    }

    private fun initSpannableStringForgotPassword() {
        val forgotPasswordButton =
            SpannableStringBuilder(requireContext().getString(R.string.sign_in_forgot_password_button_text))
        val spanStart = 0
        val spanFinish = forgotPasswordButton.length

        forgotPasswordButton.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.actionAuthorizationFragmentToPasswordRecoveryFragment)
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
        val registrationButton =
            SpannableStringBuilder(requireContext().getString(R.string.sign_in_open_sign_up_button_text))
        val spanStart = 0
        val spanFinish = registrationButton.length

        registrationButton.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.actionAuthorizationFragmentToRegistrationFragment)
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
        val appCompatActivity = requireActivity() as? AppCompatActivity
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}
