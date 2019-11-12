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
        @Column(name = "_id", nullable = false)
        val id: Long = 1,
        @Column(name = "first_name", nullable = false)
        val firstName: String = "",
        @Column(name = "last_name", nullable = false)
        val lastName: String = "",
        @Column(name = "patronymic")
        val patronymic: String? = null,
        @Column(name = "birth_date", nullable = false)
        val birthDate: LocalDate = LocalDate.now(),
        @Column(name = EMAIL, nullable = false, unique = true)
        val eMail: String = "",
        @Column(name = "sex", nullable = false)
        val sex: String = "",
        @Column(name = PHONE_NUMBER, nullable = false, unique = true)
        val phoneNumber: String = "",
        @Column(name = "_password", nullable = false)
        val password: String = ""
) {
    companion object {
        const val EMAIL = "email"
        const val PHONE_NUMBER = "phone_number"
    }
}