package tools

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

object Utils {

    //region s

    fun s(locator: String): SelenideElement {
        return Selenide.`$`(By.cssSelector(locator))
    }

    fun SelenideElement.s(locator: String): SelenideElement {
        return this.`$`(By.cssSelector(locator))
    }

    //endregion

    //region ss

    fun ss(locator: String): ElementsCollection {
        return Wait.waitForAndReturnElements({ Selenide.`$$`(By.cssSelector(locator)) }, 2)
    }

    //endregion
}