package com.core.conventions

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class ConventionsArchitecturalCheckTest {
    @Test
    fun `clean architecture layers have correct dependencies`() {
        Konsist.scopeFromProject()
            .assertArchitecture {
                // Define layers
                val domain = Layer("Domain", "com.feature.domain..")
                val presentation = Layer("Presentation", "com.feature.ui..")
                val data = Layer("Data", "com.feature.data..")

                // Define dependencies
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }
}