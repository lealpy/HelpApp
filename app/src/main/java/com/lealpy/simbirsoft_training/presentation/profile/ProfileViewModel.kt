package com.lealpy.simbirsoft_training.presentation.profile

import android.app.Activity
import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val appUtils: AppUtils
) : AndroidViewModel(application) {

    private val _avatarUser = MutableLiveData<Bitmap>()
    val avatarUser : LiveData<Bitmap> = _avatarUser

    private val _avatarFriend1 = MutableLiveData<Bitmap>()
    val avatarFriend1 : LiveData<Bitmap> = _avatarFriend1

    private val _avatarFriend2 = MutableLiveData<Bitmap>()
    val avatarFriend2 : LiveData<Bitmap> = _avatarFriend2

    private val _avatarFriend3 = MutableLiveData<Bitmap>()
    val avatarFriend3 : LiveData<Bitmap> = _avatarFriend3

    init {
        Single.create<Map<String, Bitmap>> { emitter ->
            emitter.onSuccess(
                mapOf(
                    FRIEND_1_IMAGE_URL to appUtils.getBitmapFromUrl(FRIEND_1_IMAGE_URL),
                    FRIEND_2_IMAGE_URL to appUtils.getBitmapFromUrl(FRIEND_2_IMAGE_URL),
                    FRIEND_3_IMAGE_URL to appUtils.getBitmapFromUrl(FRIEND_3_IMAGE_URL)
                )
            )
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmaps ->
                    _avatarFriend1.value = bitmaps[FRIEND_1_IMAGE_URL]
                    _avatarFriend2.value = bitmaps[FRIEND_2_IMAGE_URL]
                    _avatarFriend3.value = bitmaps[FRIEND_3_IMAGE_URL]
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                }
            )
    }

    private fun showToast() {
        appUtils.showToast(getApplication<Application>().getString(R.string.click_heard))
    }

    fun onEditClicked() { showToast() }

    fun onExitButtonClicked() { showToast() }

    fun onGotCameraResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap = result.data?.extras?.get("data") as Bitmap
            val croppedBitmap = appUtils.cropBitmap(bitmap, BITMAP_RATIO)
            _avatarUser.value = croppedBitmap
        }
    }

    fun onGotGalleryResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageURI = result.data?.data
            if(selectedImageURI != null) {
                val bitmap = appUtils.getBitmapFromUri(selectedImageURI)
                val croppedBitmap = appUtils.cropBitmap(bitmap, BITMAP_RATIO)
                _avatarUser.value = croppedBitmap
            }
        }
    }

    fun onDeletePhotoSelected() {
        _avatarUser.value = getApplication<Application>().getDrawable(R.drawable.no_photo)?.toBitmap()
    }

    companion object {
        private const val BITMAP_RATIO = 18.0 / 11.0
        private const val FRIEND_1_IMAGE_URL = "https://user-images.githubusercontent.com/90380451/149196125-b3fe5703-386f-4842-bb2a-10f3d0b7284f.png"
        private const val FRIEND_2_IMAGE_URL = "https://user-images.githubusercontent.com/90380451/149196129-d97e1509-ae98-4cf0-82cd-fa3b2e59a5c9.png"
        private const val FRIEND_3_IMAGE_URL = "https://user-images.githubusercontent.com/90380451/149196131-e8f334c6-b755-424b-be42-5093b62cd5e8.png"
    }

}
