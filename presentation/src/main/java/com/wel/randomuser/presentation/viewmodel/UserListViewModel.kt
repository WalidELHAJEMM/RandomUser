package com.wel.randomuser.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.wel.randomuser.domain.interactor.GetUserListUseCase
import com.wel.randomuser.presentation.utils.CoroutineContextProvider
import com.wel.randomuser.presentation.utils.DataState
import com.wel.randomuser.presentation.utils.UiAwareLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val userListUseCase: GetUserListUseCase,
) : BaseViewModel(contextProvider) {

    private val _userList = UiAwareLiveData<DataState>()
    private var userList: LiveData<DataState> = _userList

    fun getRandomUsers(): LiveData<DataState> {
        return userList
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _userList.postValue(DataState.Error(exception.message ?: "Error"))
    }

    fun getUsers() {
        _userList.postValue(DataState.Loading)
        launchCoroutineIO {
            loadUsers()
        }
    }

    private suspend fun loadUsers() {
        userListUseCase(Unit).collect {
            _userList.postValue(DataState.Success(it))
        }
    }

}
