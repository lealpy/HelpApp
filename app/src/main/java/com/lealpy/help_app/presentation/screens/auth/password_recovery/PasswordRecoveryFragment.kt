package com.lealpy.help_app.presentation.screens.auth.password_recovery

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentPasswordRecoveryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordRecoveryFragment : Fragment(R.layout.fragment_password_recovery) {

    private lateinit var binding: FragmentPasswordRecoveryBinding

    private val viewModel: PasswordRecoveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPasswordRecoveryBinding.bind(view)
        initViews()
        initObservers()
        initToolbar()
    }

    private fun initObservers() {
        viewModel.popBackStack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.restorePasswordBtn.setOnClickListener {
            viewModel.onRestorePasswordClicked(binding.emailEditText.text.toString())
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