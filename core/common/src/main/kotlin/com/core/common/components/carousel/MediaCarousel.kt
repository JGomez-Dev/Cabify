package com.core.common.components.carousel

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.core.common.R
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.core.common.utils.extensions.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


data class MediaCarouselData(val text: String, val image: String, val code: String)

@Composable
fun MediaCarousel(
    theme: CabifyTheme = Cabify.theme,
    list: LazyPagingItems<MediaCarouselData>,
    totalItemsToShow: Int = 10,
    carouselLabel: String = "",
    autoScrollDuration: Long  = 3000L,
    onItemClicked: (MediaCarouselData) -> Unit
) {
    val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(
                            page = nextPage,
                            animationSpec = tween(
                                durationMillis = 800
                            )
                        )
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = theme.padding.padding32),
                pageSpacing = theme.padding.padding16
            ) { page: Int ->
                val item: MediaCarouselData? = list[page]
                item?.let {
                    Card(
                        onClick = { onItemClicked(it) },
                        modifier = Modifier.carouselTransition(
                            page,
                            pagerState
                        )
                    ) {
                        CarouselBox(theme, it)
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(
                text = carouselLabel,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun CarouselBox(theme: CabifyTheme, data: MediaCarouselData) {
    Box {
        AsyncImage(
            model = data.image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
            error = painterResource(id = R.drawable.ic_load_error),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(theme.dimension.sizeDimension128)
                .fillMaxWidth()
        )
        val gradient = remember {
            Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    Color(0xE6000000)
                )
            )
        }

        Text(
            style = theme.semanticTextStyle.cabifyButton(false),
            text = data.text,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(theme.padding.padding16)
        )
    }
}

@Composable
@Preview
fun MediaCarouselPreview(){
    val mediaList = listOf(
        MediaCarouselData("Promoción 1", "https://www.thebalancemoney.com/thmb/At8Phg5iioy6iKbid9EeDAKJFDc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/credit-card-664122404-5aa02284c673350037bce86d.jpg", code = ""),
        MediaCarouselData("Promoción 2", "https://www.thebalancemoney.com/thmb/At8Phg5iioy6iKbid9EeDAKJFDc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/credit-card-664122404-5aa02284c673350037bce86d.jpg", code = ""),
        MediaCarouselData("Promoción 3", "https://www.thebalancemoney.com/thmb/At8Phg5iioy6iKbid9EeDAKJFDc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/credit-card-664122404-5aa02284c673350037bce86d.jpg", code = ""),
        MediaCarouselData("Promoción 4", "https://www.thebalancemoney.com/thmb/At8Phg5iioy6iKbid9EeDAKJFDc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/credit-card-664122404-5aa02284c673350037bce86d.jpg", code = ""),
        MediaCarouselData("Promoción 5", "https://www.thebalancemoney.com/thmb/At8Phg5iioy6iKbid9EeDAKJFDc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/credit-card-664122404-5aa02284c673350037bce86d.jpg", code = "")
    )
    val pagingData = PagingData.from(mediaList)
    val fakeDataFlow = MutableStateFlow(pagingData)

    MediaCarousel(onItemClicked = {}, list = fakeDataFlow.collectAsLazyPagingItems())
}