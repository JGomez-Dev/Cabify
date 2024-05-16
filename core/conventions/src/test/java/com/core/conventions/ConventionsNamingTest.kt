package com.core.conventions

import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class ConventionsNamingTest {

    @TestFactory
    fun `naming convention`(): Stream<DynamicTest> =
        KonsistScope.scope.classes().withKnownParents().stream().map { klass ->
            DynamicTest.dynamicTest("${klass.name} should follow convention") {
                klass.assertTrue(testName = "${klass.name} should follow convention") { it.name.complyWithTheConvention() }
            }
        }

    private fun <T : KoParentProvider> List<T>.withKnownParents(): List<T> = filter {
        it.parents.any { parent ->
            parent.hasNameEndingWith("ViewModel") ||
                    parent.hasNameEndingWith("UseCase") ||
                    parent.hasNameEndingWith("Repository")
        }
    }

    private fun String.complyWithTheConvention() = any {
        endsWith("ViewModel") ||
                endsWith("UseCase") ||
                endsWith("RepositoryImpl")
    }
}
