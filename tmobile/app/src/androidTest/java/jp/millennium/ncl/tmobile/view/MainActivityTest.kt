package jp.millennium.ncl.tmobile.view

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import jp.millennium.ncl.tmobile.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    /**
     * UI test
     * 1. launch app
     * 2. show Charity list screen
     * 3. input all on Charity donation screen
     * 4. show Success screen
     * 5. go home
     */
    @Test
    fun mainActivityTest() {
        val linearLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.charityList),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        Thread.sleep(5000)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.amount),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("2000"), closeSoftKeyboard())

        val creditCardEditText = onView(
            allOf(
                withId(R.id.cardNumber),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        creditCardEditText.perform(replaceText("4242 4242 4242 4242"), closeSoftKeyboard())

        val cardNameEditText = onView(
            allOf(
                withId(R.id.cardName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        cardNameEditText.perform(replaceText("tanaka"), closeSoftKeyboard())

        val expiryDateEditText = onView(
            allOf(
                withId(R.id.expiryDate),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        expiryDateEditText.perform(replaceText("12"), closeSoftKeyboard())

        val expiryDateEditText2 = onView(
            allOf(
                withId(R.id.expiryDate), withText("12/"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        expiryDateEditText2.perform(replaceText("12/22"))

        val expiryDateEditText3 = onView(
            allOf(
                withId(R.id.expiryDate), withText("12/22"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        expiryDateEditText3.perform(closeSoftKeyboard())

        val securityCodeEditText = onView(
            allOf(
                withId(R.id.securityCode),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        securityCodeEditText.perform(replaceText("111"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.submit), withText("Submit"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        Thread.sleep(5000)

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.goHome), withText("Go Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        Thread.sleep(5000)

        val textView = onView(
            allOf(
                withText("Charity List"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Charity List")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
