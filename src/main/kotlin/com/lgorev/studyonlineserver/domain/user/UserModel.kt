package com.lgorev.studyonlineserver.domain.user

import java.time.LocalDate
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserModel(
        val id: Long = 0,
        @Size(min = 1, max = 15)
        val firstName: String = "",
        @Size(min = 1, max = 15)
        val lastName: String = "",
        @Size(min = 1, max = 15)
        val patronymic: String? = null,
        val birthDate: LocalDate = LocalDate.now(),
        @Pattern(regexp = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
        val mail: String = "",
        val sex: String = "",
        val phoneNumber: String= "",
        val password: String= ""
)
