package com.lealpy.help_app.presentation.screens.profile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.lealpy.help_app.databinding.DialogFragmentPhotoBinding
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_CHOOSE_PHOTO_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_DELETE_PHOTO_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_DIALOG_RESULT_KEY
import com.lealpy.help_app.presentation.utils.Const.PROFILE_FEATURE_TAKE_PHOTO_KEY

class PhotoDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentPhotoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentPhotoBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        initViews()
        return builder.create()
    }

    private fun initViews() {
        binding.choosePhotoBtn.setOnClickListener {
            setDialogResult(PROFILE_FEATURE_CHOOSE_PHOTO_KEY)
        }

        binding.takePhotoBtn.setOnClickListener {
            setDialogResult(PROFILE_FEATURE_TAKE_PHOTO_KEY)
        }

        binding.deletePhotoBtn.setOnClickListener {
            setDialogResult(PROFILE_FEATURE_DELETE_PHOTO_KEY)
        }
    }

    private fun setDialogResult(selectedItemKey: String) {
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle
            ?.set(PROFILE_FEATURE_DIALOG_RESULT_KEY, selectedItemKey)
        dismiss()
    }

}
