package com.lgorev.studyonlineserver.domain

data class RequestPageModel(
        val page: Int,
        val size: Int,
        val sortedType: SortedType = SortedType.ASC,
        val sortedBy: String? = null

)

enum class SortedType {
    ASC, DESC
}