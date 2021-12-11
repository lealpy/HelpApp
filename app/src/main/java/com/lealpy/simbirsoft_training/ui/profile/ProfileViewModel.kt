package com.lealpy.simbirsoft_training.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _photo = MutableLiveData<Int> ()
    val photo : LiveData<Int> = _photo

    fun setPhoto(photo : Int) {
        _photo.value = photo
    }

}