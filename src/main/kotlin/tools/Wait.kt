package tools

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.impl.WebElementsCollectionWrapper
import java.lang.Thread.sleep

object Wait {
    fun waitFor(body: () -> Boolean, seconds: Int = 3): Boolean {
        for (i in 0 until seconds) {
            if (!run(body))
                sleep(1000)
            else
                return true
        }
        return false
    }

    fun <T> waitForAndReturnElements(body: () -> List<T>, seconds: Int = 3): ElementsCollection {
        for (i in 0 until seconds) {
            val result = run(run { body })
            if (result.isEmpty())
                sleep(1000)
            else {
                sleep(500)
                val reResult = run(run { body })
                return if (reResult.size == result.size)
                    result as ElementsCollection
                else
                    reResult as ElementsCollection
            }
        }
        return ElementsCollection(WebElementsCollectionWrapper(WebDriverRunner.driver(), emptyList()))
    }
}



