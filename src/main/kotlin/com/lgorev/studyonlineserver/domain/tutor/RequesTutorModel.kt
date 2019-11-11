package com.lgorev.studyonlineserver.domain.tutor

import java.time.LocalDate

data class RequestTutorModel(
        val id: Int,
        val experience: LocalDate,
        val info: String,
        val userId: Long
)