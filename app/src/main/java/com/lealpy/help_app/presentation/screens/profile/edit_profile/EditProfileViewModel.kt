package com.lealpy.help_app.presentation.screens.profile.edit_profile

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.EditUserUseCase
import com.lealpy.help_app.domain.use_cases.firebase.GetUserUseCase
import com.lealpy.help_app.presentation.model.DatePickerData
import com.lealpy.help_app.presentation.model.UserUi
import com.lealpy.help_app.presentation.screens.auth.sign_up.SignUpViewModel
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
class EditProfileViewModel @Inject constructor(
    private val editUserUseCase: EditUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val validators: PresentationValidators,
    private val utils: PresentationUtils,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _userUi = MutableLiveData<UserUi>()
    val userUi: LiveData<UserUi> = _userUi

    private val _navigateTo = MutableLiveData<Int>()
    val navigateTo : LiveData<Int> = _navigateTo

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _datePickerData = MutableLiveData<DatePickerData>(null)
    val datePickerData: LiveData<DatePickerData> = _datePickerData

    private val _dateOfBirth = MutableLiveData<String>(null)
    val dateOfBirth: LiveData<String> = _dateOfBirth

    private var dateOfBirthTimestamp = 0L

    private val disposable = CompositeDisposable()

    init {
        getUser()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSaveChangesClicked(
        surname: String,
        name: String,
        dateOfBirth: String,
        fieldOfActivity: String,
    ) {
        when {
            !validators.isValidName(name) -> {
                utils.showToast(utils.getString(R.string.auth_invalid_name))
            }
            !validators.isValidName(surname) -> {
                utils.showToast(utils.getString(R.string.auth_invalid_surname))
            }
            dateOfBirth.isBlank() -> {
                utils.showToast(utils.getString(R.string.auth_invalid_date_of_birth))
            }
            !validators.isValidFieldOfActivity(fieldOfActivity) -> {
                utils.showToast(utils.getString(R.string.auth_invalid_field_of_activity))
            }
            else -> {
                _progressBarVisibility.value = View.VISIBLE

                disposable.add(
                    editUserUseCase(
                        surname = surname,
                        name = name,
                        dateOfBirth = dateOfBirthTimestamp,
                        fieldOfActivity = fieldOfActivity,
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                _navigateTo.value =
                                    R.id.actionEditProfileFragmentToNavigationProfile
                            },
                            { error ->
                                Log.e(Const.LOG_TAG, error.message.toString())
                                _progressBarVisibility.value = View.GONE
                            }
                        )
                )
            }
        }
    }

    private fun getUser() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getUserUseCase()
                .observeOn(Schedulers.io())
                .map { user ->
                    dateOfBirthTimestamp = user.dateOfBirth
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
                        Log.e(Const.LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    fun onDateOfBirthClicked() {
        val year = mappers.getYearIntByTimestamp(dateOfBirthTimestamp)
        val month = mappers.getMonthIntByTimestamp(dateOfBirthTimestamp)
        val day = mappers.getDayIntByTimestamp(dateOfBirthTimestamp)

        val datePickerData = DatePickerData(year, month - 1, day)
        _datePickerData.value = datePickerData
    }

    fun onDateOfBirthPicked(year: Int, month: Int, dayOfMonth: Int) {
        dateOfBirthTimestamp = mappers.getTimestampByInt(year, month, dayOfMonth, 0, 0)
        _dateOfBirth.value = mappers.getDateStringByTimestamp(dateOfBirthTimestamp)
    }

}
