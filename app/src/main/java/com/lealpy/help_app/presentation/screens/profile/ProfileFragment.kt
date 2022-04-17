package com.lealpy.help_app.presentation.screens.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.R
import com.lealpy.help_app.databinding.FragmentProfileBinding
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_CHOOSE_PHOTO_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_DELETE_PHOTO_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_DIALOG_RESULT_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_TAKE_PHOTO_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    private val readStoragePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotReadStoragePermissionResult
    )

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::onGotGalleryResult
    )

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotCameraPermissionResult
    )

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::onGotCameraResult
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        initToolbar()
        initObservers()
        initViews()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
    }

    private fun initObservers() {
        viewModel.settingGetPush.observe(viewLifecycleOwner) { settingGetPush ->
            binding.switcherGetPush.isChecked = settingGetPush
        }

        viewModel.progressBarVisibility.observe(viewLifecycleOwner) { progressBarVisibility ->
            binding.progressBar.visibility = progressBarVisibility
        }

        viewModel.userUi.observe(viewLifecycleOwner) { userUi ->
            binding.surnameAndName.text = "${userUi.surname} ${userUi.name}"
            binding.dateOfBirth.text = userUi.dateOfBirth
            binding.fieldOfActivity.text = userUi.fieldOfActivity
            userUi.avatar?.let { binding.avatarUser.setImageBitmap(it) }
                ?: binding.avatarUser.setImageResource(R.drawable.no_photo)
        }

        try {
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<String>(PROFILE_FEATURE_DIALOG_RESULT_KEY)
                ?.observe(viewLifecycleOwner) { selectedItemKey ->
                    when (selectedItemKey) {
                        PROFILE_FEATURE_CHOOSE_PHOTO_KEY -> readStoragePermissionLauncher.launch(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        PROFILE_FEATURE_TAKE_PHOTO_KEY -> cameraPermissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                        PROFILE_FEATURE_DELETE_PHOTO_KEY -> viewModel.onDeletePhotoSelected()
                    }
                }
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
        }
    }

    private fun initViews() {
        binding.avatarUser.setOnClickListener {
            findNavController().navigate(R.id.action_navigationProfileToPhotoDialogFragment)
        }

        binding.signOutButton.setOnClickListener {
            viewModel.onSignOutClicked()
            findNavController().navigate(R.id.actionNavigationProfileToAuthorizationFragment)
        }

        binding.switcherGetPush.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSettingGetPushChanged(isChecked)
        }
    }

    /** Get photo from gallery */

    private fun onGotReadStoragePermissionResult(granted: Boolean) {
        if (granted) {
            choosePhotoFromGallery()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
            ) {
                askUserForOpeningAppSettings(requireContext().getString(R.string.profile_alert_storage_permission_denied_title))
            }
        }
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        galleryLauncher.launch(galleryIntent)
    }

    private fun onGotGalleryResult(result: ActivityResult) {
        viewModel.onGotGalleryResult(result)
    }

    /** Get photo from camera */

    private fun onGotCameraPermissionResult(granted: Boolean) {
        if (granted) {
            takePhotoOnCamera()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.CAMERA)
            ) {
                askUserForOpeningAppSettings(requireContext().getString(R.string.profile_alert_camera_permission_denied_title))
            }
        }
    }

    private fun takePhotoOnCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun onGotCameraResult(result: ActivityResult) {
        viewModel.onGotCameraResult(result)
    }

    /** Ask user for open android settings */

    private fun askUserForOpeningAppSettings(title: String) {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireActivity().packageName, null)
        )

        if (requireActivity().packageManager.resolveActivity(appSettingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY) != null
        ) {
            AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(R.string.permission_denied_forever_message)
                .setPositiveButton(R.string.open) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profileToolbarEdit -> {
                findNavController().navigate(R.id.actionNavigationProfileToEditProfileFragment)
            }
        }
        return true
    }

}
