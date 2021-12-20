package com.lealpy.simbirsoft_training.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.lealpy.simbirsoft_training.databinding.FragmentPhotoDialogBinding

class PhotoDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentPhotoDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = FragmentPhotoDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(context)

        builder.setView(binding.root)

        initViews()

        return builder.create()
    }

    private fun initViews() {

        binding.choosePhotoBtn.setOnClickListener {
            parentFragmentManager.setFragmentResult(PHOTO_DIALOG_REQUEST_KEY, bundleOf(
                SELECTED_ITEM_KEY to SELECTED_CHOOSE_PHOTO))
            dismiss()
        }

        binding.makePhotoBtn.setOnClickListener {
            parentFragmentManager.setFragmentResult(PHOTO_DIALOG_REQUEST_KEY, bundleOf(
                SELECTED_ITEM_KEY to SELECTED_MAKE_PHOTO))
            dismiss()
        }

        binding.deleteBtn.setOnClickListener {
            parentFragmentManager.setFragmentResult(PHOTO_DIALOG_REQUEST_KEY, bundleOf(
                SELECTED_ITEM_KEY to SELECTED_DELETE_PHOTO))
            dismiss()
        }

    }

    fun setupListener(
        fragmentManager: FragmentManager,
        lifecycleOwner: LifecycleOwner,
        listener: (Int) -> Unit
    ) {
        fragmentManager.setFragmentResultListener(
            PHOTO_DIALOG_REQUEST_KEY,
            lifecycleOwner,
            { _, result ->
                listener.invoke(result.getInt(SELECTED_ITEM_KEY))
            }
        )
    }

    companion object {
        const val SELECTED_ITEM_KEY = "SELECTED_ITEM_KEY"
        const val PHOTO_DIALOG_TAG = "PHOTO_DIALOG_TAG"
        const val PHOTO_DIALOG_REQUEST_KEY = "PHOTO_DIALOG_REQUEST_KEY"

        const val SELECTED_CHOOSE_PHOTO = 1
        const val SELECTED_MAKE_PHOTO = 2
        const val SELECTED_DELETE_PHOTO = 3
    }
}
