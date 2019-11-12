package com.lgorev.studyonlineserver.domain.tutor

import com.lgorev.studyonlineserver.domain.user.UserModel
import java.time.LocalDate

data class TutorModel (
        val id: Long,
        val experience: LocalDate,
        val info: String,
        val user: UserModel
)
