package com.lgorev.studyonlineserver.repositories.tutor

import com.lgorev.studyonlineserver.repositories.user.UserEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tutor")
data class TutorEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "_id", nullable = false)
        val id: Int = 0,
        @Column(name = "experience", nullable = false)
        val experience: LocalDate = LocalDate.now(),
        @Column(name = "_info")
        val info: String = "",
        @Column(name = "user_id")
        val userId: Long,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "_id", insertable = false, updatable = false)
        val user: UserEntity? = null
)

