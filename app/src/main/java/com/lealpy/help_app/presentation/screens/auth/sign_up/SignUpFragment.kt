package com.lealpy.help_app.presentation.screens.auth.sign_up

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        initViews()
        initObservers()
        initToolbar()
    }

    private fun initViews() {
        binding.loginScreenBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.dateOfBirthEditText.setOnClickListener {
            viewModel.onDateOfBirthClicked()
        }

        binding.signUpBtn.setOnClickListener {
            viewModel.onSignUpBtnClicked(
                name = binding.nameEditText.text.toString(),
                surname = binding.surnameEditText.text.toString(),
                dateOfBirth = binding.dateOfBirthEditText.text.toString(),
                fieldOfActivity = binding.fieldOfActivityEditText.text.toString(),
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString(),
                repeatPassword = binding.repeatPasswordEditText.text.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateTo ->
            findNavController().navigate(navigateTo)
        }

        viewModel.isSignUpBtnEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.signUpBtn.isEnabled = isEnabled
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }

        viewModel.dateOfBirth.observe(viewLifecycleOwner) { dateOfBirth ->
            binding.dateOfBirthEditText.setText(dateOfBirth)
        }

        val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            viewModel.onDateOfBirthPicked(year, month + 1, dayOfMonth)
        }

        viewModel.datePickerData.observe(viewLifecycleOwner) { datePickerData ->
            if (datePickerData != null) {
                DatePickerDialog(
                    requireContext(),
                    datePickerListener,
                    datePickerData.year,
                    datePickerData.month,
                    datePickerData.day
                ).show()
            }
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.setSupportActionBar(binding.toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().popBackStack()
        }
        return true
    }

}