package com.lealpy.help_app.presentation.screens.profile

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.GetUserUseCase
import com.lealpy.help_app.domain.use_cases.firebase.SaveAvatarToStorageUseCase
import com.lealpy.help_app.domain.use_cases.firebase.SignOutUseCase
import com.lealpy.help_app.domain.use_cases.prefs.GetSettingGetPushUseCase
import com.lealpy.help_app.domain.use_cases.prefs.SetSettingGetPushUseCase
import com.lealpy.help_app.presentation.model.UserUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getSettingGetPushUseCase: GetSettingGetPushUseCase,
    private val setSettingGetPushUseCase: SetSettingGetPushUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveAvatarToStorageUseCase: SaveAvatarToStorageUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val utils: PresentationUtils,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _userUi = MutableLiveData<UserUi>()
    val userUi: LiveData<UserUi> = _userUi

    private val _settingGetPush = MutableLiveData<Boolean>()
    val settingGetPush: LiveData<Boolean> = _settingGetPush

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val disposable = CompositeDisposable()

    init {
        getUser()
        getSettings()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onGotCameraResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap = result.data?.extras?.get("data") as Bitmap
            val croppedBitmap = utils.cropBitmap(bitmap, BITMAP_RATIO)
            setAvatar(croppedBitmap)
        }
    }

    fun onGotGalleryResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageURI = result.data?.data
            if (selectedImageURI != null) {
                val bitmap = utils.getBitmapFromUri(selectedImageURI)
                val croppedBitmap = utils.cropBitmap(bitmap, BITMAP_RATIO)
                setAvatar(croppedBitmap)
            }
        }
    }

    fun onDeletePhotoSelected() {
        utils.getBitmapById(R.drawable.no_photo)?.let { setAvatar(it) }
    }

    fun onSignOutClicked() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            signOutUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    fun onSettingGetPushChanged(settingGetPush: Boolean) {
        if (_settingGetPush.value != settingGetPush) {
            setSettingGetPushUseCase(settingGetPush)
            _settingGetPush.value = settingGetPush
        }
    }

    private fun getUser() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getUserUseCase()
                .observeOn(Schedulers.io())
                .map { user ->
                    mappers.userToUserUi(user)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userUi ->
                        _userUi.value = userUi
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    private fun getSettings() {
        _settingGetPush.value = getSettingGetPushUseCase()
    }

    private fun setAvatar(bitmap: Bitmap) {
        _progressBarVisibility.value = View.VISIBLE
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, BITMAP_QUALITY, baos)
        val data = baos.toByteArray()

        disposable.add(
            saveAvatarToStorageUseCase(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        getUser()
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    private fun showToast(text: String = utils.getString(R.string.click_heard)) {
        utils.showToast(text)
    }

    companion object {
        private const val BITMAP_RATIO = 18.0 / 11.0
        private const val BITMAP_QUALITY = 100
    }

}
