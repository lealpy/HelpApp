package com.lealpy.simbirsoft_training.presentation.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

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

    private val viewModel : ProfileViewModel by activityViewModels()

    private val photoDialogFragment = PhotoDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        initToolbar()
        initObservers()
        initViews()
        initDialogListener()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
    }

    private fun initObservers() {
        viewModel.avatarFriend1.observe(viewLifecycleOwner) { avatarFriend1 ->
            binding.avatarFriend1.setImageBitmap(avatarFriend1)
        }

        viewModel.avatarFriend2.observe(viewLifecycleOwner) { avatarFriend2 ->
            binding.avatarFriend2.setImageBitmap(avatarFriend2)
        }

        viewModel.avatarFriend3.observe(viewLifecycleOwner) { avatarFriend3 ->
            binding.avatarFriend3.setImageBitmap(avatarFriend3)
        }

        viewModel.avatarUser.observe(viewLifecycleOwner) { avatarUser ->
            binding.avatarUser.setImageBitmap(avatarUser)
        }
    }

    private fun initViews() {
        binding.avatarUser.setOnClickListener {
            showDialog()
        }

        binding.exitButton.setOnClickListener {
            viewModel.onExitButtonClicked()
        }
    }

    /** Profile dialog fragment */

    private fun showDialog() {
        photoDialogFragment.show(parentFragmentManager, PhotoDialogFragment.PHOTO_DIALOG_TAG)
    }

    private fun initDialogListener() {
        photoDialogFragment.setListener(parentFragmentManager, viewLifecycleOwner) { selectedItem ->
            when(selectedItem) {
                PhotoDialogFragment.SELECTED_CHOOSE_PHOTO -> readStoragePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                PhotoDialogFragment.SELECTED_MAKE_PHOTO -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                PhotoDialogFragment.SELECTED_DELETE_PHOTO -> viewModel.onDeletePhotoSelected()
            }
        }
    }

    /** Get photo from gallery */

    private fun onGotReadStoragePermissionResult(granted: Boolean) {
        if (granted) {
            choosePhotoFromGallery()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                askUserForOpeningAppSettings(requireContext().getString(R.string.profile_alert_storage_permission_denied_title))
            }
        }
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        galleryLauncher.launch(galleryIntent)
    }

    private fun onGotGalleryResult(result : ActivityResult) {
        viewModel.onGotGalleryResult(result)
    }

    /** Get photo from camera */

    private fun onGotCameraPermissionResult(granted: Boolean) {
        if (granted) {
            takePhotoOnCamera()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
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

    private fun askUserForOpeningAppSettings(title : String) {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireActivity().packageName, null)
        )

        if (requireActivity().packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
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
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profileToolbarEdit -> viewModel.onEditClicked()
        }
        return true
    }

}
