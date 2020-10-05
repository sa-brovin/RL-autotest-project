package pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.By
import tools.Utils.s

abstract class BasePage {

    open val url = ""
    open val pageContainer: By
        get() = By.cssSelector("")

    open fun isOpened(): Boolean {
        return if (!WebDriverRunner.hasWebDriverStarted())
            false
        else
            return s(this.pageContainer).isDisplayed
    }

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

    fun reload() {
        Selenide.refresh()
    }
}