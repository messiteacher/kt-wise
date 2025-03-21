package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying
import global.AppConfig
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository{

    private var lastId: Int = 0

    init {
        initTable()
    }

    val tableDirPath: Path
        get() = AppConfig.tableDirPath.resolve("wiseSaying")

    override fun save(wiseSaying: WiseSaying): WiseSaying {

        if (wiseSaying.isNew()) {

            val new = wiseSaying.copy(id = ++lastId)
            saveOnDisk(new)

            return new
        }

        saveOnDisk(wiseSaying)

        return wiseSaying
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonStr)
    }

    override fun findAll(): List<WiseSaying> {
        return listOf()
    }

    override fun findById(id: Int): WiseSaying? {
        return null
    }

    override fun delete(wiseSaying: WiseSaying) {

    }

    override fun clear() {

    }

    fun saveLastId(i: Int) {
        tableDirPath.resolve("lastId.txt").toFile().writeText(i.toString())
    }

    fun loadLastId(): Int {
        return tableDirPath.resolve("lastId.txt").toFile().readText().toIntOrNull() ?: 0
    }

    fun initTable() {

        tableDirPath.toFile().run {
            if (!exists()) {
                mkdirs()
            }
        }
    }
}