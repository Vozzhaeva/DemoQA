/**
 * Created by Administrator on 1/29/2018.
 */
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertTrue;


public abstract class AssertExtension {

    WebElement webElement;

    private static boolean isElementPresent(WebElement element) {

        return element.isDisplayed();
    }

    public static void assertIsElementPresent(WebElement element) {
        assertTrue("!-----Element not found: " + element.getText() + " ------!", isElementPresent(element));

    }


    public static void assertData(WebElement element, String expectedResult) {

        assertTrue("!-----Incorrect text of the field.The true valie is: " + element.getText() + " ------!", element.getText().equalsIgnoreCase(expectedResult));
    }
}
