/**
 * 주어진 이름을 랜덤하게 출력하는 프로그램을 작성.
 * 몇 개의 이름을 출력할 것인지는 사용자가 입력할 수 있어야 한다.
 */
fun main() {
    val arguments = listOf("철희", "성빈", "찬우", "현식", "은지", "예림", "도훈")
    val namesV1 = Randomize.random(arguments, 3)
    println(namesV1)

    val namesV2 = Randomize.random("철희", "성빈", "찬우", "현식", "은지", "예림", "도훈", count = 3)
    println(namesV2)
}

object Randomize {
    fun random(
        names: List<String>,
        count: Int = 1,
    ): List<String> {
        Validator.validateCountInRange(names, count)
        val randomNames = names.shuffled()
        return randomNames.take(count)
    }

    fun random(
        vararg name: String,
        count: Int = 1,
    ): List<String> {
        val names = name.toList()
        Validator.validateCountInRange(names, count)
        val randomNames = names.shuffled()
        return randomNames.take(count)
    }
}

object Validator {
    fun validateCountInRange(
        names: List<String>,
        count: Int,
    ) {
        if (names.isEmpty()) throw IllegalArgumentException("names must not be empty")
        if (count !in 1..names.size) throw IllegalArgumentException("size must be between 1 and ${names.size}")
    }
}