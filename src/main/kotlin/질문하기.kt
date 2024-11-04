/**
 * 스터디에서 꼭 한 사람은 질문을 해야합니다.
 * 질문을 누가 누구에게 해야하는지 circular하게 질문하도록 정하는 프로그램입니다.
 *
 * ex) 철희, 현주, 범진, 재영
 * 철희 -> 현주 -> 범진 -> 재영 -> 철희
 * 와 같이 질문을 circular하게 진행합니다.
 * 만약에 질문을 하지 않는다면 뿅망치로 뚝배기를 박살내겠습니다.
 */

fun main() {
    val names = listOf("철희", "현주", "범진", "재영")
    CircularQuestion.execute(names)
}

object CircularQuestion {
    fun execute(
        names: List<String>,
    ) {
        val nameToString = names.shuffled().joinToString("") { "$it -> " }
        val result = nameToString + names.first()
        println(result)
    }

    fun execute(
        vararg name: String,
    ) {
        val names = name.toList().shuffled()
        val nameToString = names.joinToString { "$it -> " }
        val result = nameToString + names.first()
        println(result)
    }
}