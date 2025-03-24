package global

import domain.wiseSaying.entity.WiseSaying

data class Page(

    val content: List<WiseSaying>,
    val totalCount: Int,
    val page: Int,
    val pageSize: Int
){
    val totalPages: Int
        get() = Math.ceil(totalCount.toDouble() / pageSize).toInt()
}