package com.lealpy.simbirsoft_training.ui.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.*
import androidx.activity.result.ActivityResultLauncher
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

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    private val readStoragePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotReadStoragePermissionResult
    )

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotCameraPermissionResult
    )

    private val viewModel : ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initMenu()
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)

        initViews()
        initDialogListener()
        initGalleryLauncher()
        initCameraLauncher()

    }

    private fun initMenu() {
        setHasOptionsMenu(true);
    }

    private fun initViews() {
        binding.avatarUser.setOnClickListener {
            showDialog()
        }
    }

    private fun initDialogListener() {
        PhotoDialogFragment().setupListener(parentFragmentManager, requireActivity()) { selectedItem ->
            when(selectedItem) {
                PhotoDialogFragment.SELECTED_CHOOSE_PHOTO -> readStoragePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                PhotoDialogFragment.SELECTED_MAKE_PHOTO -> cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                PhotoDialogFragment.SELECTED_DELETE_PHOTO -> binding.avatarUser.setImageResource(R.drawable.no_photo)
            }
        }
    }

    private fun initGalleryLauncher() {
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageURI = result.data?.data
                if(selectedImageURI != null) {
                    val inputStream = requireActivity().contentResolver.openInputStream(selectedImageURI)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val croppedBitmap = cropBitmap(bitmap)
                    binding.avatarUser.setImageBitmap(croppedBitmap)
                }
            }
        }
    }

    private fun onGotReadStoragePermissionResult(granted: Boolean) {
        if (granted) {
            choosePhotoFromGallery()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                askUserForOpeningAppSettings(requireActivity().getString(R.string.profile_alert_storage_permission_denied_title))
            }
        }
    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        galleryLauncher.launch(galleryIntent)
    }

    private fun initCameraLauncher() {
        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val bitmap = result.data?.extras?.get("data") as Bitmap
                    val croppedBitmap = cropBitmap(bitmap)
                    binding.avatarUser.setImageBitmap(croppedBitmap)
                }
            }
    }

    private fun onGotCameraPermissionResult(granted: Boolean) {
        if (granted) {
            takePhotoOnCamera()
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
                askUserForOpeningAppSettings(requireActivity().getString(R.string.profile_alert_camera_permission_denied_title))
            }
        }
    }

    private fun takePhotoOnCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

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

    private fun cropBitmap(bitmap: Bitmap) : Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            (bitmap.width / photoWidthHeight).toInt()
        )
    }

    private fun showDialog() {
        PhotoDialogFragment().show(parentFragmentManager, PhotoDialogFragment.PHOTO_DIALOG_TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.edit -> viewModel.onMenuEditClicked()
        }
        return true
    }

    companion object {
        private const val photoWidthHeight = 18.0 / 11.0
    }

}
