package com.lealpy.help_app.presentation.screens.auth.password_recovery

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.RestorePasswordUseCase
import com.lealpy.help_app.presentation.utils.Const
import com.lealpy.help_app.presentation.utils.PresentationUtils
import com.lealpy.help_app.presentation.utils.PresentationValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val utils: PresentationUtils,
    private val restorePasswordUseCase: RestorePasswordUseCase,
    private val validators: PresentationValidators,
) : ViewModel() {

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _popBackStack = MutableLiveData<Boolean>()
    val popBackStack: LiveData<Boolean> = _popBackStack

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onRestorePasswordClicked(email: String) {
        when {
            !validators.isValidEmail(email) -> {
                showToast(utils.getString(R.string.auth_invalid_email))
            }
            else -> {
                _progressBarVisibility.value = View.VISIBLE

                disposable.add(
                    restorePasswordUseCase(email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                _progressBarVisibility.value = View.GONE
                                showToast(utils.getString(R.string.password_recovery_check_mail))
                                _popBackStack.value = true
                            },
                            { error ->
                                Log.e(Const.LOG_TAG, error.message.toString())
                                _progressBarVisibility.value = View.GONE
                                showToast(utils.getString(R.string.password_recovery_wrong_email))
                            }
                        )
                )
            }
        }
    }

    private fun showToast(text: String) {
        utils.showToast(text)
    }

}
