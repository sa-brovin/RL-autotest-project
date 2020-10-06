package elements

import com.codeborne.selenide.SelenideElement

open class ContactRow(row: SelenideElement) {
    private val r = row

    // Работа со списком будет слишком медленна.
    // val name: String get() = r.ss("td").exclude(Condition.attribute("rowspan")).first().text()
    // val address: String get() = r.ss("td").exclude(Condition.attribute("rowspan")).last().text()

    // Парсинг текста работает быстрее
    val name: String get() = r.text().split("\n")[0]
    val address: String get() = r.text().split("\n")[1]

    fun select() {
        r.click()
    }

    fun show() {
        r.scrollIntoView(("{block: \"center\", inline: \"nearest\"}"))
    }
}