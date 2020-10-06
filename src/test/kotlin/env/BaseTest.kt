package env

import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.qameta.allure.Step
import lib.ChromeDriverProvider
import org.testng.annotations.*
import tools.StringUtils


@Suppress("UNUSED_PARAMETER")
abstract class BaseTest {

    @BeforeSuite
    fun setUp() {
        Configuration.timeout = 6000
        Configuration.reopenBrowserOnFail = true
        Configuration.fastSetValue = true
        // Debug
        Configuration.browserPosition = "960x0"
        Configuration.browserSize = "1600x900"
        Configuration.browser = ChromeDriverProvider::class.java.name
    }

    // После выполнения всех тестов в сьюте, закрываем драйвер
    @AfterSuite
    fun tearDownSuite() {
        Selenide.close()
    }

    @Step("{0}")
    fun step(description: String, body: () -> Unit) {
        run(body)
    }

    @Step("{0}")
    fun sbt(description: String, body: () -> Boolean) {
        assertk.assert(run(body), description).isTrue()
    }

    @Step("{0}")
    fun sbe(description: String, testedString: String, expectedString: String) {
        assertk.assert(StringUtils.normalizeString(testedString), description).isEqualTo(StringUtils.normalizeString(expectedString))
    }
}