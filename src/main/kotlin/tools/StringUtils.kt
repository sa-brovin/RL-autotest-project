package tools

object StringUtils {
    fun generateString(len: Int): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghijklmnopqrstuvwxyz"
        return (allowedChars).map { it }.shuffled().subList(0, len).joinToString("")
    }

    fun normalizeString(str: String): String {
        return str.replace("\n", "").replace(" ", "").replace(",", "")
            .replace("-", "").replace("\r", "").toLowerCase()
    }
}