package com.lgorev.studyonlineserver.services

import com.lgorev.studyonlineserver.repositories.tutor.TutorRepository
import org.springframework.stereotype.Service

@Service
class TutorService(val tutorRepository: TutorRepository) {
    fun findAll() = tutorRepository.findAll()
}