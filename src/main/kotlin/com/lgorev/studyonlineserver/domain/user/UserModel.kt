package com.lgorev.studyonlineserver.domain.user

import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserModel(
        val id: Long = 0,
        @Size(min = 1, max = 15)
        @NotNull val firstName: String,
        @Size(min = 1, max = 15)
        @NotNull val lastName: String,
        @Size(min = 1, max = 15)
        val patronymic: String?,
        @NotNull val birthDate: LocalDate,
        @Pattern(regexp = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")
        @NotNull val mail: String,
        @NotNull val sex: String,
        @NotNull val phoneNumber: String,
        @NotNull val password: String
)
