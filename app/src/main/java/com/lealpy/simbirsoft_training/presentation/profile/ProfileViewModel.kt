package com.lealpy.simbirsoft_training.presentation.profile

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.lealpy.simbirsoft_training.R

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    fun onMenuEditClicked() {
        Toast.makeText(
            getApplication(),
            getApplication<Application>().resources.getString(R.string.profile_edit_click_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}
