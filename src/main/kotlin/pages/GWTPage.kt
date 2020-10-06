package pages

import com.codeborne.selenide.SelenideElement
import elements.ContactRow
import org.openqa.selenium.Keys
import tools.CalendarUtils
import tools.Utils.s
import tools.Utils.ss
import tools.Wait
import java.lang.Exception
import java.util.*


class GWTPage : BasePage() {
    override val url: String get() = "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellList"

    private val contactCounter: SelenideElement get() = s(".gwt-HTML[dir='ltr']")
    val contactsSize: Int get() = contactCounter.text().split(": ")[1].toInt()

    private val firstNameParentRow: SelenideElement
        get() = ss(".gwt-DecoratorPanel table tr").first {
            it.text().contains("First Name:")
        }
    val firstNameInput: SelenideElement get() = firstNameParentRow.s("input")

    val lastNameInput: SelenideElement
        get() {
            val parent = ss(".gwt-DecoratorPanel table tr").first {
                it.text().contains("Last Name:")
            }
            return parent.s("input")
        }
    val birthDateInput: SelenideElement get() = s(".gwt-DecoratorPanel input.gwt-DateBox")
    private val datePicker: SelenideElement get() = s(".datePickerDays")
    val categorySelector: SelenideElement get() = s(".gwt-DecoratorPanel select.gwt-ListBox")
    val addressInput: SelenideElement get() = s(".gwt-DecoratorPanel textarea")

    val createContactBtn: SelenideElement get() = ss(".gwt-DecoratorPanel button").first { it.text() == "Create Contact" }

    private val cellList: List<ContactRow> get() = ss("#gwt-debug-contentPanel [onclick='']").map { ContactRow(it) }

    // Локатор не однозначный (GNHGC04CJJ), но судя по тому что при каждом запуске тестов он одинаков, считаем что это нормальне имя класса
    private val scrollableCellList: SelenideElement get() = s("#gwt-debug-contentPanel .GNHGC04CJJ")

    fun changeFirstName(newFirstName: String) {
        firstNameInput.clear()
        firstNameInput.sendKeys(newFirstName)
    }

    fun changeLastName(newLastName: String) {
        lastNameInput.clear()
        lastNameInput.sendKeys(newLastName)
    }

    fun selectCategory(category: String) {
        categorySelector.selectOption(category)
    }

    fun changeDate(newDate: Calendar) {
        birthDateInput.clear()
        val formattedDate = CalendarUtils.calendarToString(newDate, "MMMM d, yyyy", Locale.ENGLISH)
        birthDateInput.sendKeys(formattedDate)
        // Ждет появления datePicker, затем нажимает Enter. Иначе datePicker остается не закрытым.
        Wait.waitFor({
            if (datePicker.isDisplayed) {
                birthDateInput.sendKeys(Keys.ENTER)
                false
            } else
                true
        })
    }

    fun changeAddress(newAddress: String) {
        addressInput.clear()
        addressInput.sendKeys(newAddress)
    }

    fun getContactRow(contactName: String): ContactRow {
        var notFound = true
        var i = 0
        // Вычислим кол-во скролов, с учетом того что за одну итерацию скроллинга добавляется 20 элементов
        val iterationCount = this.contactsSize / 20 + 1
        while (i < iterationCount && notFound) {
            // Медленный поиск.
            //notFound = !cellList.any { it.nameText == contactName }

            // Быстрый поиск.
            notFound = !scrollableCellList.text().contains(contactName)

            cellList.last().show()
            i++
        }
        if (!notFound)
        // Переворачиваем список, для более быстрого поиска элемента. Так как судя по механике списка, элементы добавляются всегда в конец.
            return cellList.asReversed().first { it.name == contactName }
        else
            throw Exception("Контакт с именем $contactName, не найден")
    }

    // Костыль для обновления списка после создания контакта. Иначе контакт не появляется в списке.
    fun updateContactList() {
        cellList.first().show()
        cellList.last().show()
    }
}