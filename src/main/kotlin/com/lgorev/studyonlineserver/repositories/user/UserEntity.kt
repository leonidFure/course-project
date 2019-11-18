package com.lgorev.studyonlineserver.repositories.user

import com.lgorev.studyonlineserver.repositories.tutor.TutorEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "usr",
        uniqueConstraints = [
            UniqueConstraint(columnNames = arrayOf("email")),
            UniqueConstraint(columnNames = arrayOf("phone_number"))
        ]
)
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = ID, nullable = false)
        val id: Long = 1,
        @Column(name = FIRST_NAME, nullable = false)
        val firstName: String = "",
        @Column(name = LAST_NAME, nullable = false)
        val lastName: String = "",
        @Column(name = PATRONYMIC)
        val patronymic: String? = null,
        @Column(name = BIRTH_DATE, nullable = false)
        val birthDate: LocalDate = LocalDate.now(),
        @Column(name = EMAIL, nullable = false, unique = true)
        val eMail: String = "",
        @Column(name = SEX, nullable = false)
        val sex: String = "",
        @Column(name = PHONE_NUMBER, nullable = false, unique = true)
        val phoneNumber: String = "",
        @Column(name = PASSWORD, nullable = false)
        val password: String = ""
) {
    companion object {
        const val ID = "_id"
        const val EMAIL = "email"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val PATRONYMIC = "patronymic"
        const val BIRTH_DATE = "phone_number"
        const val SEX = "sex"
        const val PHONE_NUMBER = "phone_number"
        const val PASSWORD = "_password"
    }
}