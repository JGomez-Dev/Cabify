package composables

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.core.common.components.button.CABIFY_BUTTON_TAG
import com.core.common.components.button.CabifyButton
import com.jgomez.cabifytest.MainActivity
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CabifyButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    @Test
    fun testCabifyButton() {
        var clicked = false

        composeTestRule.activity.setContent {
            CabifyButton(text = "Click Me") {
                clicked = true
            }
        }

        composeTestRule.onNodeWithTag(CABIFY_BUTTON_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText("Click Me").assertIsDisplayed()
        composeTestRule.onNodeWithTag(CABIFY_BUTTON_TAG).performClick()

        assertTrue(clicked)
    }
}