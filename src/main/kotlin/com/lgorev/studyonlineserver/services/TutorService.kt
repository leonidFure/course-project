package com.lgorev.studyonlineserver.services

import com.lgorev.studyonlineserver.domain.tutor.RequestTutorModel
import com.lgorev.studyonlineserver.domain.tutor.TutorModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.exceptions.NotFoundException
import com.lgorev.studyonlineserver.repositories.tutor.TutorEntity
import com.lgorev.studyonlineserver.repositories.tutor.TutorRepository
import com.lgorev.studyonlineserver.repositories.user.UserEntity
import com.lgorev.studyonlineserver.repositories.user.UserRepository
import org.springframework.stereotype.Service

@Service
class TutorService(val tutorRepository: TutorRepository, val userRepository: UserRepository) {
    fun findAll() = tutorRepository.findAll().map { e -> e }
    @Throws(NotFoundException::class)
    fun save(model: RequestTutorModel) {
        userRepository.findById(model.userId).orElseThrow {
            NotFoundException("User with id: ${model.userId} already exists.")
        }
        tutorRepository.save(model.toEntity())
    }


    fun TutorEntity.toModel() = TutorModel(id, experience, info, user?.toModel() ?: UserModel())

    fun RequestTutorModel.toEntity() = TutorEntity(id, experience, info, userId)

    fun UserEntity.toModel() = UserModel(id, firstName, lastName, patronymic, birthDate, eMail, sex, phoneNumber, password)
}