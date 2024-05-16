package com.core.common.error

import com.core.common.error.model.GlobalErrorHandlerModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class GlobalErrorManager @Inject constructor(private val defaultErrorMapper: GlobalErrorMapper) {

    private val _event = MutableSharedFlow<GlobalErrorHandlerModel>()
    val event: SharedFlow<GlobalErrorHandlerModel> = _event.asSharedFlow()

    suspend fun emitError(
        throwable: Throwable,
        onGenericError: (suspend () -> Unit)? = null,
        onUnauthorized: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null,
    ) {
        emitErrorType(
            defaultErrorMapper.map(throwable),
            onGenericError = onGenericError,
            onUnauthorized = onUnauthorized,
            onNetworkUnavailable = onNetworkUnavailable,
            dismissAction = dismissAction,
        )
    }

    private suspend fun emitErrorType(
        globalErrorType: GlobalErrorType,
        onGenericError: (suspend () -> Unit)? = null,
        onUnauthorized: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null,
    ) {
        _event.emit(
            GlobalErrorHandlerModel(
                globalErrorType = globalErrorType,
                onGenericError = onGenericError,
                onUnauthorized = onUnauthorized,
                onNetworkUnavailable = onNetworkUnavailable,
                dismissAction = dismissAction,
            )
        )
    }
}