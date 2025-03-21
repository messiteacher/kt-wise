import domain.system.controller.SystemController
import domain.wiseSaying.controller.WiseSayingController
import global.Request

class App {

    fun run() {

        val wiseSayingController = WiseSayingController()
        val systemController = SystemController()

        println("== 명언 앱 ==")
        while (true) {

            print("명령) ")
            val input = readlnOrNull() ?: ""

            val rq = Request(input)

            when (rq.actionName) {

                "종료" -> {
                    systemController.exit()
                    break
                }
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.list()
                "삭제" -> wiseSayingController.delete(rq)
                "수정" -> wiseSayingController.modify(rq)
                else -> {
                    println("알 수 없는 명령입니다.")
                }
            }
        }
    }
}