package com.lealpy.simbirsoft_training.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private val viewModel : ProfileViewModel by activityViewModels()

    private val photoDialogFragment = PhotoDialogFragment()

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
        photoDialogFragment.setupListener(parentFragmentManager, requireActivity()) { selectedItem ->
            when(selectedItem) {
                PhotoDialogFragment.SELECTED_MAKE_PHOTO -> setupCamera()
                PhotoDialogFragment.SELECTED_DELETE_PHOTO -> binding.avatarUser.setImageResource(R.drawable.no_photo)
            }
        }
    }

    private fun initCameraLauncher() {
        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val bitmap = result.data?.extras?.get("data") as Bitmap

                    val croppedBitmapWidth = bitmap.width
                    val croppedBitmapHeight = (bitmap.width / photoWidthHeight).toInt()

                    val croppedBitmap = Bitmap.createBitmap(
                        bitmap,
                        0,
                        0,
                        croppedBitmapWidth,
                        croppedBitmapHeight
                    )

                    binding.avatarUser.setImageBitmap(croppedBitmap)
                }
            }
    }

    private fun setupCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun showDialog() {
        photoDialogFragment.show(parentFragmentManager, PhotoDialogFragment.PHOTO_DIALOG_TAG)
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
