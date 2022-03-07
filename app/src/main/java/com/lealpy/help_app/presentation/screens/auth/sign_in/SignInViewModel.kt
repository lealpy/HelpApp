package com.lealpy.help_app.presentation.screens.auth.sign_in

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseNetworkException
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.SignInUseCase
import com.lealpy.help_app.presentation.utils.Const.AUTHORIZATION_FEATURE_EMAIL_KEY
import com.lealpy.help_app.presentation.utils.Const.AUTHORIZATION_FEATURE_PASSWORD_KEY
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationUtils
import com.lealpy.help_app.presentation.utils.PresentationValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val utils: PresentationUtils,
    private val signInUseCase: SignInUseCase,
    private val validators: PresentationValidators,
) : ViewModel() {

    private val _navigateTo = MutableLiveData<Int>()
    val navigateTo: LiveData<Int> = _navigateTo

    private val _isSignInBtnEnabled = MutableLiveData<Boolean>()
    val isSignInBtnEnabled: LiveData<Boolean> = _isSignInBtnEnabled

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSignInBtnClicked(email: String, password: String) {
        when {
            !validators.isValidEmail(email) -> {
                showToast(utils.getString(R.string.auth_invalid_email))
            }
            !validators.isValidPassword(password) -> {
                showToast(utils.getString(R.string.auth_invalid_password))
            }
            else -> {
                _progressBarVisibility.value = View.VISIBLE
                disposable.add(
                    signInUseCase(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                _navigateTo.value = R.id.actionAuthorizationFragmentToNavigationHelp
                                _progressBarVisibility.value = View.GONE
                            },
                            { error ->
                                Log.e(LOG_TAG, error.message.toString())
                                _progressBarVisibility.value = View.GONE
                                when (error) {
                                    is FirebaseNetworkException -> {
                                        showToast(utils.getString(R.string.firebase_network_exception))
                                    }
                                    else -> {
                                        showToast(utils.getString(R.string.sign_in_unsuccessful_auth))
                                    }
                                }
                            }
                        )
                )
            }
        }
    }

    fun onEditTextLengthWatcherInit(observable: Observable<Map<String, String>>) {
        disposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pair ->
                        val email = pair[AUTHORIZATION_FEATURE_EMAIL_KEY] ?: ""
                        val password = pair[AUTHORIZATION_FEATURE_PASSWORD_KEY] ?: ""
                        _isSignInBtnEnabled.value = validators.isValidEmail(email)
                                && validators.isValidPassword(password)
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    },
                )
        )
    }

    private fun showToast(text: String = utils.getString(R.string.click_heard)) {
        utils.showToast(text)
    }

}
