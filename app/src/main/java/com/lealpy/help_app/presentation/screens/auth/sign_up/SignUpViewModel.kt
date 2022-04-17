package com.lealpy.help_app.presentation.screens.auth.sign_up

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.SignUpUseCase
import com.lealpy.help_app.presentation.model.DatePickerData
import com.lealpy.help_app.presentation.utils.Const
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import com.lealpy.help_app.presentation.utils.PresentationValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val utils: PresentationUtils,
    private val signUpUseCase: SignUpUseCase,
    private val validators: PresentationValidators,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _navigateTo = MutableLiveData<Int>()
    val navigateTo: LiveData<Int> = _navigateTo

    private val _isSignUpBtnEnabled = MutableLiveData<Boolean>()
    val isSignUpBtnEnabled: LiveData<Boolean> = _isSignUpBtnEnabled

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _datePickerData = MutableLiveData<DatePickerData>(null)
    val datePickerData: LiveData<DatePickerData> = _datePickerData

    private val _dateOfBirth = MutableLiveData<String>(null)
    val dateOfBirth: LiveData<String> = _dateOfBirth

    private var dateOfBirthTimestamp = INITIAL_DATE_OF_BIRTH

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSignUpBtnClicked(
        name: String,
        surname: String,
        dateOfBirth: String,
        fieldOfActivity: String,
        email: String,
        password: String,
        repeatPassword: String,
    ) {
        when {
            !validators.isValidName(name) -> {
                showToast(utils.getString(R.string.auth_invalid_name))
            }
            !validators.isValidName(surname) -> {
                showToast(utils.getString(R.string.auth_invalid_surname))
            }
            dateOfBirth.isBlank() -> {
                showToast(utils.getString(R.string.auth_invalid_date_of_birth))
            }
            !validators.isValidFieldOfActivity(fieldOfActivity) -> {
                showToast(utils.getString(R.string.auth_invalid_field_of_activity))
            }
            !validators.isValidEmail(email) -> {
                showToast(utils.getString(R.string.auth_invalid_email))
            }
            !validators.isValidPassword(password) -> {
                showToast(utils.getString(R.string.auth_invalid_password))
            }
            password != repeatPassword -> {
                showToast(utils.getString(R.string.auth_passwords_not_match))
            }
            else -> {
                _progressBarVisibility.value = View.VISIBLE

                val capitalizedName = validators.getCapitalizedString(name)
                val capitalizedSurname = validators.getCapitalizedString(surname)
                val capitalizedFieldOfActivity = validators.getCapitalizedString(fieldOfActivity)

                disposable.add(
                    signUpUseCase(
                        name = capitalizedName,
                        surname = capitalizedSurname,
                        dateOfBirth = dateOfBirthTimestamp,
                        fieldOfActivity = capitalizedFieldOfActivity,
                        email = email,
                        password = password
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                _navigateTo.value =
                                    R.id.actionRegistrationFragmentToNavigationProfile
                                _progressBarVisibility.value = View.GONE
                                showToast(utils.getString(R.string.auth_sign_in_success))
                            },
                            { error ->
                                Log.e(Const.LOG_TAG, error.message.toString())
                                _progressBarVisibility.value = View.GONE
                                when (error) {
                                    is FirebaseAuthUserCollisionException -> {
                                        showToast(utils.getString(
                                            R.string.auth_email_exists))
                                    }
                                    is FirebaseNetworkException -> {
                                        showToast(utils.getString(R.string.firebase_network_exception))
                                    }
                                    else -> {
                                        showToast(utils.getString(R.string.auth_sign_in_unsuccess))
                                    }
                                }
                            }
                        )
                )
            }
        }
    }

    private fun showToast(text: String = utils.getString(R.string.click_heard)) {
        utils.showToast(text)
    }

    fun onDateOfBirthClicked() {
        val year = mappers.getYearIntCurrentTimezoneByTimestamp(dateOfBirthTimestamp)
        val month = mappers.getMonthIntCurrentTimezoneByTimestamp(dateOfBirthTimestamp)
        val day = mappers.getDayIntCurrentTimezoneByTimestamp(dateOfBirthTimestamp)

        val datePickerData = DatePickerData(year, month - 1, day)
        _datePickerData.value = datePickerData
    }

    fun onDateOfBirthPicked(year: Int, month: Int, dayOfMonth: Int) {
        dateOfBirthTimestamp = mappers.getTimestampGmtByInt(year, month, dayOfMonth, 0, 0)
        _dateOfBirth.value = mappers.getDateStringByTimestamp(dateOfBirthTimestamp)
    }

    companion object {
        private const val INITIAL_DATE_OF_BIRTH = 946684800000L
    }

}
