package com.offer.domain

import com.core.common.test.MainCoroutineScopeRule
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel
import com.offer.domain.repository.PromotionRepository
import com.offer.domain.usecase.GetPromotionsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class GetPromotionsUseCaseTest {
    @JvmField
    @RegisterExtension
    val coroutinesExtension = MainCoroutineScopeRule()

    private var promotionRepository: PromotionRepository = mockk()

    private lateinit var getPromotionsUseCase: GetPromotionsUseCase

    @BeforeEach
    fun setup() {
        getPromotionsUseCase = GetPromotionsUseCase(promotionRepository)
    }

    @Test
    fun `invoke should return list of PromotionModel`() = coroutinesExtension.runTest {
        // Given
        val mockPromotionRepository = mockk<PromotionRepository>()
        val getPromotionsUseCase = GetPromotionsUseCase(mockPromotionRepository)

        val mockPromotionModels = listOf(
            PromotionModel(
                code = "PROMO_CODE_1",
                text = "PROMO_TEXT_1",
                offer = PromotionOfferModel.BuyOneGetOneFree,
                image = "image_1"
            ),
            PromotionModel(
                code = "PROMO_CODE_2",
                text = "PROMO_TEXT_2",
                offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0),
                image = "image_2"
            )
        )

        // Simulate repository providing mock promotions
        coEvery { mockPromotionRepository.getPromotions() } returns mockPromotionModels

        // When
        val result = getPromotionsUseCase()

        // Then
        assertEquals(mockPromotionModels, result)
    }

}