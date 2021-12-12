package com.lealpy.simbirsoft_training.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private val viewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);

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

    private fun initViews() {
        binding.avatarUser.setOnClickListener {
            showDialog()
        }
    }

    private fun initDialogListener() {
        PhotoDialogFragment().setupListener(parentFragmentManager, requireActivity()) { selectedItem ->
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
                    binding.avatarUser.setImageBitmap(bitmap)
                }
            }
    }

    private fun setupCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
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
            R.id.edit -> Toast.makeText(
                requireContext(),
                requireActivity().getString(R.string.profile_edit_click_message),
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

}
