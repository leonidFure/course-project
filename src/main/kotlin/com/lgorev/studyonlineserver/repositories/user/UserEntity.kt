package com.lgorev.studyonlineserver.repositories.user

import com.lgorev.studyonlineserver.repositories.tutor.TutorEntity
import org.hibernate.validator.constraints.UniqueElements
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
        val id: Long,
        @Column(name = "first_name", nullable = false)
        val firstName: String,
        @Column(name = "last_name", nullable = false)
        val lastName: String,
        @Column(name = "patronymic")
        val patronymic: String?,
        @Column(name = "birth_date", nullable = false)
        val birthDate: LocalDate,
        @Column(name = "email", nullable = false, unique = true)
        val eMail: String,
        @Column(name = "sex", nullable = false)
        val sex: String,
        @Column(name = "phone_number", nullable = false, unique = true)
        val phoneNumber: String,
        @Column(name = "_password", nullable = false)
        val password: String,
        @OneToOne(mappedBy = "usr")
        val tutor: TutorEntity
)