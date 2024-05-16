package com.offer.data

import com.core.common.test.MainCoroutineScopeRule
import com.core.network.feature.offer.dto.PromotionDTO
import com.core.network.feature.offer.dto.PromotionOfferDTO
import com.core.network.feature.offer.repository.OfferDataProviders
import com.offer.data.mapper.toPromotionModel
import com.offer.data.repository.PromotionRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class PromotionRepositoryImplTest {

    @JvmField
    @RegisterExtension
    val coroutinesExtension = MainCoroutineScopeRule()

    @Test
    fun `getPromotions should return list of PromotionModel`() = coroutinesExtension.runTest {
        // Given
        val mockOfferDataProviders = mockk<OfferDataProviders>()
        val promotionRepository = PromotionRepositoryImpl(mockOfferDataProviders)

        val mockPromotionDTOs = listOf(
            PromotionDTO("CODE_1", offer = PromotionOfferDTO(minimumQuantity = 3, discountPerItem = 2.0), text = "text_1", image = "image_1"),
            PromotionDTO("CODE_2", offer = PromotionOfferDTO(value = 10.0), text = "text_1", image = "image_1")
        )

        // Simulate repository providing mock offers
        coEvery { mockOfferDataProviders.getOffers() } returns mockPromotionDTOs

        // When
        val result = promotionRepository.getPromotions()

        // Then
        assertEquals(mockPromotionDTOs.map { it.toPromotionModel() }, result)
    }

}