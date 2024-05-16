package composables

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.core.common.components.card.CABIFY_CARD_PRODUCT_ITEM_PLUS_TAG
import com.core.common.components.card.CardProductItem
import com.jgomez.cabifytest.MainActivity
import com.jgomez.cabifytest.R
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardProductItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun testCardProductItemTest() {
        val productName = "Product Name"
        val productPrice = "10"
        val productTotalWithOffers = 8.0
        val productQuantity = 2
        val code = "code"
        val image = R.drawable.cabify
        var clicked = false

        composeTestRule.activity.setContent {
            CardProductItem(
                name = productName,
                price = productPrice,
                totalWithOffers = productTotalWithOffers,
                quantity = productQuantity,
                onIncreaseClicked = {
                    clicked = true
                },
                onDecreaseClicked = {},
                image = image,
                offerCodeClicked = null,
                code =code,
                contentPromotion = {}
            )
        }

        composeTestRule.onNodeWithText("Product Name").assertIsDisplayed()
        composeTestRule.onNodeWithTag(CABIFY_CARD_PRODUCT_ITEM_PLUS_TAG).performClick()
        assertTrue(clicked)
    }
}