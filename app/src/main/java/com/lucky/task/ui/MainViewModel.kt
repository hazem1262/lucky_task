package com.lucky.task.ui

import com.lucky.task.base.BaseViewModel
import com.lucky.task.utils.coroutines.ContextProviders
import javax.inject.Inject

class MainViewModel @Inject constructor(contextProvider: ContextProviders) : BaseViewModel(contextProvider)