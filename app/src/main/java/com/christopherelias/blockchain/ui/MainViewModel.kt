package com.christopherelias.blockchain.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christopherelias.blockchain.core.functional_programming.Failure
import com.christopherelias.blockchain.features.home.domain.repository.HomeRepository
import com.christopherelias.blockchain.ui.models.TransactionsPerSecond
import com.christopherelias.blockchain.utils.OneTimeEvent
import com.christopherelias.blockchain.utils.toOneTimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeState = MutableLiveData(HomeState())
    val homeState: LiveData<HomeState> = _homeState

    init {
        getHomeContent()
    }

    private fun getHomeContent() {
        // Initial State
        _homeState.value = _homeState.value?.copy(isLoading = true)

        // Execute
        viewModelScope.launch {
            repository.getTransactionsPerSecond()
                .either(::handleHomeContentFailure, ::handleHomeContentSuccess)
        }
    }

    private fun handleHomeContentFailure(
        failure: Failure
    ) {
        _homeState.value = _homeState.value?.copy(
            isLoading = false,
            failure = failure.toOneTimeEvent()
        )
    }

    private fun handleHomeContentSuccess(
        transactionsPerSecond: TransactionsPerSecond
    ) {
        _homeState.value = _homeState.value?.copy(
            isLoading = false,
            transactionsPerSecond = transactionsPerSecond,
            failure = null
        )
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val transactionsPerSecond: TransactionsPerSecond = TransactionsPerSecond.idle(),
    val failure: OneTimeEvent<Failure>? = null
)