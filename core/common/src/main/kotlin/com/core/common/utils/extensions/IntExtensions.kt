package com.core.common.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

inline val Int.dpToSp: TextUnit
    @Composable @ReadOnlyComposable get() = LocalDensity.current.run { dp.toSp() }