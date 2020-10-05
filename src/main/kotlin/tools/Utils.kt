package tools

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

object Utils {

    //region s

    fun s(locator: By): SelenideElement {
        return Selenide.`$`(locator)
    }

    fun s(locator: String): SelenideElement {
        return Selenide.`$`(By.cssSelector(locator))
    }

    fun SelenideElement.s(locator: String): SelenideElement {
        return this.`$`(By.cssSelector(locator))
    }

    fun SelenideElement.s(locator: By): SelenideElement {
        return this.`$`(locator)
    }
    //endregion

    //region ss

    fun ss(locator: By): ElementsCollection {
        return Wait.waitForAndReturnElements({ Selenide.`$$`(locator) }, 2)
    }

    fun ss(locator: String): ElementsCollection {
        return Wait.waitForAndReturnElements({ Selenide.`$$`(By.cssSelector(locator)) }, 2)
    }

    fun SelenideElement.ss(locator: String): ElementsCollection {
        return Wait.waitForAndReturnElements({ this.`$$`(By.cssSelector(locator)) }, 2)
    }

    //endregion
}