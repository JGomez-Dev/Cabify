package com.core.conventions

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.verify.assertTrue
import java.util.stream.Stream
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class ConventionsTesteableClassesTest {

    @TestFactory
    fun `testeable classes should have test`(): Stream<DynamicTest> {
        val testClasses = Konsist.scopeFromTest().classes()

        return KonsistScope.scope.classes().testeableClasses().filter { it.name != "FlowUseCase" }.stream().map { useCase ->
            DynamicTest.dynamicTest("${useCase.name} should have test") {
                useCase.assertTrue(testName = "${useCase.name} should have test") { testClasses.any { it.name == useCase.name + "Test" } }
            }
        }
    }

    private fun <T : KoNameProvider> List<T>.testeableClasses(): List<T> = filter {
        val testeableClasses = listOf("UseCase", "ViewModel", "RepositoryImpl")
        testeableClasses.any { suffix -> it.hasNameEndingWith(suffix) }
    }
}
