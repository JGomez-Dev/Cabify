package com.core.common.error.model

import com.core.common.error.GlobalErrorType


data class GlobalErrorHandlerModel(
    val globalErrorType: GlobalErrorType,
    val onGenericError: (suspend () -> Unit)? = null,
    val onUnauthorized: (suspend () -> Unit)? = null,
    val onNetworkUnavailable: (suspend () -> Unit)? = null,
    val dismissAction: (() ->Unit)?=null
)
