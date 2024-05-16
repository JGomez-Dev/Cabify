package com.core.common.error

import com.core.common.domain.usecase.flow.FlowUseCase
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

interface SafeFlowUseCaseDelegate {
    val globalErrorManager: GlobalErrorManager

    /** Method that handle global errors by default. Use lambdas for special cases */
    fun <T, R> FlowUseCase<T, R>.safePrepare(
        input: T,
        onGenericError: (suspend () -> Unit)? = null,
        onUnauthorized: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null
    ) =
        prepare(input).catch {
            globalErrorManager.emitError(it, onGenericError, onUnauthorized, onNetworkUnavailable, dismissAction)
        }

    /** Method that handle global errors by default without parameter for use case. Use lambdas for special cases */
    fun <R> FlowUseCase<Unit, R>.safePrepare(
        onGenericError: (suspend () -> Unit)? = null,
        onUnauthorized: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null
    ) = prepare(Unit).catch {
            globalErrorManager.emitError(it, onGenericError, onUnauthorized, onNetworkUnavailable, dismissAction)
        }

    class Default @Inject constructor(override val globalErrorManager: GlobalErrorManager) : SafeFlowUseCaseDelegate
}