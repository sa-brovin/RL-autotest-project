import env.BaseTest
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import pages.GWTPage


@Feature("Первый тест")
class FirstTest : BaseTest() {
    @DataProvider
    fun testParamsSource(): Iterator<String> {
        return listOf(
            "d",
            "e"
        ).iterator()
    }

    @Story("Успешный вход")
    @Test(dataProvider = "testParamsSource", description = "Успешный вход в систему")
    fun successfulLogin(acc: String) {
        GWTPage().open()
    }
}