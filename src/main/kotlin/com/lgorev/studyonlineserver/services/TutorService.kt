package com.lgorev.studyonlineserver.services

import com.lgorev.studyonlineserver.domain.tutor.RequestTutorModel
import com.lgorev.studyonlineserver.domain.tutor.TutorModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.exceptions.NotFoundException
import com.lgorev.studyonlineserver.repositories.tutor.TutorEntity
import com.lgorev.studyonlineserver.repositories.tutor.TutorRepository
import com.lgorev.studyonlineserver.repositories.user.UserEntity
import com.lgorev.studyonlineserver.repositories.user.UserRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TutorService(private val tutorRepository: TutorRepository,private val userRepository: UserRepository) {

    private val log = LogManager.getLogger(TutorService::class.java)

    fun findAll() = tutorRepository.findAll().map { e -> e }

    @Throws(NotFoundException::class)
    fun save(model: RequestTutorModel) {
        log.info("Try to save tutor with id: ${model.id}.")
        userRepository.findById(model.userId).orElseThrow {
            log.warn("Tutor with id: ${model.userId} already exists.")
            NotFoundException("Tutor with id: ${model.userId} already exists.")
        }
        tutorRepository.save(model.toEntity())
        log.info("User with id: ${model.userId} successfully saved.")
    }

    @Throws(NotFoundException::class)
    fun update(model: RequestTutorModel) {
        log.info("Try to save tutor with id: ${model.id}")
        userRepository.findById(model.userId).orElseThrow {
            log.warn("User with id: ${model.userId} already exists.")
            NotFoundException("User with id: ${model.userId} already exists.")
        }
        if(tutorRepository.existsById(model.id)) {
            tutorRepository.save(model.toEntity())
            log.info("Tutor with id: ${model.userId} successfully saved.")
        } else {
            log.warn("Tutor with id ${model.id} not exists.")
            throw NotFoundException("Tutor with id ${model.id} not exists.")
        }
    }
    @Throws(NotFoundException::class)
    fun delete(id: Long) {
        log.info("Try to delete tutor with id: $id.")
        if (!tutorRepository.existsById(id)) {
            log.warn("Tutor with id: $id not found.")
            throw NotFoundException("Tutor with id: $id not found.")
        }
        tutorRepository.deleteById(id)
        log.info("Tutor with id $id successfully deleted.")
    }

    fun TutorEntity.toModel() = TutorModel(id, experience, info, user?.toModel() ?: UserModel())

    fun RequestTutorModel.toEntity() = TutorEntity(id, experience, info, userId)

    fun UserEntity.toModel() = UserModel(id, firstName, lastName, patronymic, birthDate, eMail, sex, phoneNumber, password)

}