package lib

import com.codeborne.selenide.WebDriverProvider
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.DriverManagerType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.HashMap

// Для локального запуска\отладки
class ChromeDriverProvider : WebDriverProvider {
    override fun createDriver(capabilities: DesiredCapabilities): WebDriver {

        ChromeDriverManager.chromedriver().version("85.0.4183.83")
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup()
        return ChromeDriver(chromeOptions)
    }

    companion object {
        val chromeOptions: ChromeOptions
            get() {
                val chromeOptions = ChromeOptions()
                chromeOptions.setAcceptInsecureCerts(true)
                // Для работы в Linux. В windows из за параметра, браузер не закрывается
                chromeOptions.addArguments("--no-sandbox")
                chromeOptions.addArguments("safebrowsing-disable-download-protection");
                val prefs = HashMap<String, Any>()
                prefs["directory_upgrade"] = true
                chromeOptions.setExperimentalOption("prefs", prefs)
                return chromeOptions
            }
    }
}