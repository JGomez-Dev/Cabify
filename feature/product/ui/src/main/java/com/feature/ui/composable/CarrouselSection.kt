package com.feature.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.common.components.carousel.MediaCarousel
import com.core.common.components.carousel.MediaCarouselData
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.ui.R
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CarrouselSection(
    promotions: List<PromotionModel>,
    theme: CabifyTheme,
    offerCodeClicked: (code: String) -> Unit
) {

    val mediaList = mutableListOf<MediaCarouselData>()
    promotions.forEach { promotion ->
        mediaList.add(MediaCarouselData(text = promotion.text, image = promotion.image, code = promotion.code))
    }
    val pagingData = PagingData.from(mediaList)
    val fakeDataFlow = MutableStateFlow(pagingData)
    Column(modifier = Modifier.padding(horizontal = theme.padding.padding16)) {
        Text(
            text = stringResource(R.string.label_offers),
            style = theme.semanticTextStyle.cabifyHeading4(false),
            color = theme.semanticColor.onSecondary
        )
        HorizontalDivider(color = theme.semanticColor.onSecondary)
    }
    MediaCarousel(
        list = fakeDataFlow.collectAsLazyPagingItems(),
        onItemClicked = { mediaCarouselData -> offerCodeClicked(mediaCarouselData.code) }
    )

}

@Composable
@Preview
fun CarrouselSectionPreview(){
    val promotions = listOf(
        PromotionModel("CODE_1", offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0), text = "Offer 1", image = "image_1"),
        PromotionModel("CODE_2", offer = PromotionOfferModel.BuyOneGetOneFree, text = " Offer 2", image = "image_1")
    )
    CarrouselSection(theme = Cabify.theme, promotions = promotions, offerCodeClicked = {})
}