package com.lgorev.studyonlineserver.repositories.tutor

import com.lgorev.studyonlineserver.repositories.user.UserEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tutor")
data class TutorEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "_id", nullable = false)
        val id: Int,
        @Column(name = "experience", nullable = false)
        val experience: LocalDate,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        val user: UserEntity
)

