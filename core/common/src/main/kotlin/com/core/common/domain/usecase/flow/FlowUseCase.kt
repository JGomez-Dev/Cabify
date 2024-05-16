package com.core.common.domain.usecase.flow

import com.core.common.domain.usecase.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<T, R>(protected open val dispatcherProvider: DispatcherProvider) {

    protected open fun dispatcher(): CoroutineContext = dispatcherProvider.io()

    fun prepare(input: T) = prepareFlow(input).flowOn(dispatcher())

    /**
     * GenericFlowUseCase Return a [Flow] that will be executed in the specified [CoroutineContext] ([Dispatchers.IO] by default).
     *
     * There's no need to call to [flowOn] in subclasses.
     */
    protected abstract fun prepareFlow(input:T): Flow<R>
}