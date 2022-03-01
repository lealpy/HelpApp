package com.lealpy.help_app.presentation.screens.profile.edit_profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private lateinit var binding: FragmentEditProfileBinding

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)
        initToolbar()
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.dateOfBirthEditText.setOnClickListener {
            viewModel.onDateOfBirthClicked()
        }

        binding.saveChangesBtn.setOnClickListener {
            viewModel.onSaveChangesClicked(
                name = binding.nameEditText.text.toString(),
                surname = binding.surnameEditText.text.toString(),
                dateOfBirth = binding.dateOfBirthEditText.text.toString(),
                fieldOfActivity = binding.fieldOfActivityEditText.text.toString(),
            )
        }

        binding.dateOfBirthEditText.setOnClickListener {
            viewModel.onDateOfBirthClicked()
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

    private fun initObservers() {
        viewModel.userUi.observe(viewLifecycleOwner) { userUi ->
            binding.surnameEditText.setText(userUi.surname)
            binding.nameEditText.setText(userUi.name)
            binding.dateOfBirthEditText.setText(userUi.dateOfBirth)
            binding.fieldOfActivityEditText.setText(userUi.fieldOfActivity)
        }

        viewModel.navigateTo.observe(viewLifecycleOwner) { destination ->
            findNavController().navigate(destination)
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
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
