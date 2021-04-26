package com.jankocvirn.testpokemon.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

object ViewModelDispatcher {
    @VisibleForTesting
    var dispatcher: CoroutineDispatcher = Dispatchers.IO
}

abstract class BaseViewModel : ViewModel() {

    private val job: Job = Job()

    val scope: CoroutineScope = CoroutineScope(ViewModelDispatcher.dispatcher + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
