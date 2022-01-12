package com.lealpy.simbirsoft_training.ui.authorization

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentAuthorizationBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_authorization.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private lateinit var binding : FragmentAuthorizationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorizationBinding.bind(view)

        initViews()
        initEditTextLengthWatcher()
        initSpannableStringForgotPassword()
        initSpannableStringRegistration()
        initToolbar()
    }

    private fun initViews() {
        //Строка для тестирования:
        binding.enterBtn.isEnabled = true
        binding.enterBtn.setOnClickListener {
            findNavController().navigate(R.id.actionAuthorizationFragmentToNavigationHelp)
        }
        binding.vkIcon.setOnClickListener { showToast() }
        binding.fbIcon.setOnClickListener { showToast() }
        binding.okIcon.setOnClickListener { showToast() }
    }

    private fun initEditTextLengthWatcher() {
        Observable.create<Boolean> { emitter ->
            binding.emailEditText.addTextChangedListener {
                emitter.onNext(emailEditText.length() >= EMAIL_MIN_SYMBOLS && passwordEditText.length() >= PASSWORD_MIN_SYMBOLS)
            }
            binding.passwordEditText.addTextChangedListener {
                emitter.onNext(emailEditText.length() >= EMAIL_MIN_SYMBOLS && passwordEditText.length() >= PASSWORD_MIN_SYMBOLS)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { isEnabled ->
                    binding.enterBtn.isEnabled = isEnabled
                },
                { error ->
                    throw Exception(error.message)
                },
            )
    }

    private fun initSpannableStringForgotPassword() {
        val forgotPasswordButton = SpannableStringBuilder(activity?.getString(R.string.authorization_forgot_password_button))
        val spanStart = 0
        val spanFinish = forgotPasswordButton.length

        forgotPasswordButton.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    showToast()
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
        val registrationButton = SpannableStringBuilder(activity?.getString(R.string.authorization_registration_button))
        val spanStart = 0
        val spanFinish = registrationButton.length

        registrationButton.setSpan(
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    showToast()
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

    private fun showToast() {
        Toast.makeText(
            requireContext(),
            activity?.getString(R.string.click_heard),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true);

        val appCompatActivity = activity as? AppCompatActivity
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
        private const val EMAIL_MIN_SYMBOLS = 6
        private const val PASSWORD_MIN_SYMBOLS = 6
    }

}
