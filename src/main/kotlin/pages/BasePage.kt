package pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner


abstract class BasePage {

    open val url = ""

    open fun isOpen(): Boolean {
        if (!WebDriverRunner.hasWebDriverStarted())
            return false
        return WebDriverRunner.url().contains(this.url)
    }

    open fun isOpen(url: String): Boolean {
        if (!WebDriverRunner.hasWebDriverStarted())
            return false
        return WebDriverRunner.url().contains(url)
    }

    open fun open() {
        if (this.isOpen())
            WebDriverRunner.getWebDriver().navigate().refresh()
        else
            Selenide.open(this.url)
    }
}