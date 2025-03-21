import domain.wiseSaying.entity.WiseSaying
import global.Request

fun main() {

    val wiseSayings = mutableListOf<WiseSaying>()
    var lastId = 0

    println("== 명언 앱 ==")
    while (true) {

        print("명령) ")
        val input = readlnOrNull() ?: ""
        val rq = Request(input)

        when (rq.actionName) {

            "종료" -> break

            "등록" -> {

                print("명언: ")
                val saying = readlnOrNull() ?: ""

                print("작가: ")
                val author = readlnOrNull() ?: ""

                val id = ++lastId
                wiseSayings.add(WiseSaying(id, saying, author))

                println("${lastId}번 명언이 등록되었습니다.")
            }

            "목록" -> {

                println("번호 / 작가 / 명언")
                println("----------------------")
                wiseSayings.forEach {
                    println("${it.id} / ${it.author} / ${it.saying}")
                }
            }

            "삭제" -> {

                val id = rq.getParam("id")?.toIntOrNull()

                if (id == null) {

                    println("삭제할 명언의 번호를 입력해주세요.")
                    continue
                }

                val rst = wiseSayings.removeIf { saying -> saying.id == id }

                if (rst) {
                    println("${id}번 명언을 삭제했습니다.")
                } else {
                    println("${id}번 명언은 존재하지 않습니다.")
                }
            }

            else -> {
                println("알 수 없는 명령입니다.")
            }
        }
    }
}