package com.lealpy.simbirsoft_training.presentation.screens.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.simbirsoft_training.presentation.MainActivity
import com.lealpy.simbirsoft_training.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val viewModel : SplashScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.startBadgeNumber.observe(viewLifecycleOwner) { startBadgeNumber ->
            (requireActivity() as? MainActivity)?.badgeSubject?.onNext(startBadgeNumber)
            findNavController().navigate(R.id.actionSplashScreenFragmentToAuthorizationFragment)
        }
    }

}
