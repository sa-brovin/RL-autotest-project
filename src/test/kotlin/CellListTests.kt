import env.BaseTest
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import pages.GWTPage
import tools.CalendarUtils
import tools.StringUtils
import java.util.*


@Feature("Cell List ")
private class CellListTests : BaseTest() {
    data class CellListParams(
        val firstName: String,
        val lastName: String,
        val category: String,
        val address: String,
        val dayOfBirth: String
    )

    @DataProvider
    fun categorySource(): Iterator<CellListParams> {
        return listOf(
            CellListParams(
                StringUtils.generateString(5),
                StringUtils.generateString(10),
                "Family",
                StringUtils.generateString(15),
                "19.01.2000"
            ),
            CellListParams(
                StringUtils.generateString(5),
                StringUtils.generateString(10),
                "Friends",
                StringUtils.generateString(15),
                "01.11.1935"
            ),
            CellListParams(
                StringUtils.generateString(5),
                StringUtils.generateString(10),
                "Coworkers",
                StringUtils.generateString(15),
                "10.12.2010"
            ),
            CellListParams(
                StringUtils.generateString(5),
                StringUtils.generateString(10),
                "Businesses",
                StringUtils.generateString(15),
                "31.08.1999"
            ),
            CellListParams(
                StringUtils.generateString(5),
                StringUtils.generateString(10),
                "Contacts",
                StringUtils.generateString(15),
                "06.08.1985"
            ),
        ).iterator()
    }

    // Выполняется 1 раз для всего класса. Открытие страницы вынесено в отдельный метод, чтобы при каждом тесте страница не переоткрывалась заново.
    @BeforeClass
    fun navigateToTestPage() {
        step("Перейти на страницу GWT") { GWTPage().open() }
    }

    @Story("Работа с категориями")
    @Test(dataProvider = "categorySource", description = "Создать контакты с разными категориями")
    fun createCategorizedContact(testParams: CellListParams) {
        val dayOfBirth = CalendarUtils.stringToCalendar(testParams.dayOfBirth, "dd.MM.yyyy")
        val gwtPage = GWTPage()
        var initContactsSize = 0
        step("Получить кол-во существующих контактов") { initContactsSize = gwtPage.contactsSize }

        step("Ввести имя") { gwtPage.changeFirstName(testParams.firstName) }
        step("Ввести фамилию") { gwtPage.changeLastName(testParams.lastName) }
        step("Выбрать категорию - \"$testParams.category\"") { gwtPage.selectCategory(testParams.category) }
        step("Ввести дату рождения - \"$dayOfBirth\"") { gwtPage.changeDate(dayOfBirth) }
        step("Ввести адрес") { gwtPage.changeAddress(testParams.address) }
        step("Нажать кнопку \"Создать контакт\"") { gwtPage.createContactBtn.click() }

        step("Обновить список контактов") { gwtPage.updateContactList() }

        sbt("Счетчик контактов увеличился на количество созданных контактов") { gwtPage.contactsSize == initContactsSize + 1 }
        step("Выбрать созданный контакт") {
            gwtPage.getContactRow("${testParams.firstName} ${testParams.lastName}").select()
        }
        sbe("Имя контакта сопадает с введенным", gwtPage.firstNameInput.value, testParams.firstName)
        sbe("Фамилия контакта сопадает с введенной", gwtPage.lastNameInput.value, testParams.lastName)
        sbe("Категория контакта сопадает с выбранной", gwtPage.categorySelector.value, testParams.category)
        sbe(
            "Дата рождения совпадает с введенной датой", gwtPage.birthDateInput.value,
            CalendarUtils.calendarToString(dayOfBirth, "MMMM d, yyyy", Locale.ENGLISH)
        )
        sbe("Адресс совпадает с введенным адрессом", gwtPage.addressInput.value, testParams.address)
    }
}