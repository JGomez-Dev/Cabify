package com.core.common.theme

import androidx.compose.ui.graphics.Color

data class SemanticColor(
    var primary: Color = Color(0xFF0057E7), // Azul principal de Cabify
    var primaryVariant: Color = Color(0xFF003399), // Variante del azul principal
    var secondary: Color = Color(0xFF00C853), // Verde secundario de Cabify
    var secondaryVariant: Color = Color(0xFF009624), // Variante del verde secundario
    var background: Color = Color(0xFFF2F2F2), // Gris claro para el fondo
    var surface: Color = Color(0xFFFFFFFF), // Blanco para la superficie
    var error: Color = Color(0xFFD32F2F), // Rojo para los errores
    var onPrimary: Color = Color.White, // Texto blanco sobre el azul principal
    var onSecondary: Color = Color.Black, // Texto negro sobre el verde secundario
    var onError: Color = Color(0xFFD32F2F), // Texto rojo para errores
    var accent: Color = Color(0xFF7145D6 ), // Morado de Cabify
    var accentLight: Color = Color(0xFFB696FF ), // Morado de Cabify

    val colorElevation06: Color = Color(0x33444444),
    var n500: Color = Color(0xFF9995A9),
)
