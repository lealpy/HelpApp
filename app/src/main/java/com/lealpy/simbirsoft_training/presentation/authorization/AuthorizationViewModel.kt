package com.lealpy.simbirsoft_training.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.presentation.authorization.AuthorizationFragment.Companion.EMAIL_KEY
import com.lealpy.simbirsoft_training.presentation.authorization.AuthorizationFragment.Companion.PASSWORD_KEY
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val resourceManager: ResourceManager,
    private val appUtils: AppUtils
) : ViewModel() {

    private val _navigateTo = MutableLiveData<Int>()
    val navigateTo: LiveData<Int> = _navigateTo

    /** Начальное значение "true" для тестирования */
    private val _isEnterBtnEnabled = MutableLiveData<Boolean>(true)
    val isEnterBtnEnabled: LiveData<Boolean> = _isEnterBtnEnabled

    fun onEnterBtnClicked() {
        _navigateTo.value = R.id.actionAuthorizationFragmentToNavigationHelp
    }

    fun onVkIconClicked() { showToast() }
    fun onFbIconClicked() { showToast() }
    fun onOkIconClicked() { showToast() }
    fun onRegistrationSpanClicked() { showToast() }
    fun onForgotPasswordSpanClicked() { showToast() }

    private fun showToast() {
        appUtils.showToast(resourceManager.getString(R.string.click_heard))
    }

    fun onEditTextLengthWatcherInit(observable: Observable<Map<String, Int>>) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { lengths ->
                    val emailLength = lengths[EMAIL_KEY] ?: 0
                    val passwordLength = lengths[PASSWORD_KEY] ?: 0
                    _isEnterBtnEnabled.value = emailLength >= EMAIL_MIN_SYMBOLS && passwordLength >= PASSWORD_MIN_SYMBOLS
                },
                { error ->
                    throw Exception(error.message)
                },
            )
    }

    companion object {
        private const val EMAIL_MIN_SYMBOLS = 6
        private const val PASSWORD_MIN_SYMBOLS = 6
    }

}
