package com.core.common.domain.usecase.flow

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

inline fun <reified T> Flow<T>.collectInLifeCycle(lifecycleOwner: LifecycleOwner, noinline collector: suspend (T) -> Unit): Job =
    lifecycleOwner.lifecycleScope.launch { flowWithLifecycle(lifecycleOwner.lifecycle).onEach(collector).collect() }