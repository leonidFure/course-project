package com.lgorev.studyonlineserver.services

import com.lgorev.studyonlineserver.exceptions.NotFoundException
import com.lgorev.studyonlineserver.exceptions.UniqueConstraintException
import com.lgorev.studyonlineserver.domain.RequestPageModel
import com.lgorev.studyonlineserver.domain.SortedType
import com.lgorev.studyonlineserver.domain.SuccessResultModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.repositories.user.UserEntity
import com.lgorev.studyonlineserver.repositories.user.UserRepository
import org.apache.logging.log4j.LogManager
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
open class UserService(private val userRepository: UserRepository) {

    private val log = LogManager.getLogger(UserService::class.java)

    fun register(model: UserModel) {
        log.info("Try to register user with eMail: ${model.mail}.")
        userRepository
                .findByeMail(model.mail)
                .orElseThrow {
                    UniqueConstraintException("User with email: ${model.mail} already exists.")
                }
        userRepository
                .findByPhoneNumber(model.phoneNumber)
                .orElseThrow {
                    UniqueConstraintException("User with phone number: ${model.phoneNumber} already exists.")
                }

        log.info("User with eMail ${model.mail} successfully registered.")
        userRepository.save(model.toEntity())
    }

    @Throws(NotFoundException::class)
    fun getUserById(id: Long) = userRepository
            .findById(id)
            .orElseThrow {
                NotFoundException("User with id: $id not found.")
            }
            .toModel()

    @Throws(UniqueConstraintException::class)
    fun update(model: UserModel): SuccessResultModel {
        log.info("Try to update user with id: ${model.id}.")
        if (userRepository.existsByeMailAndIdNot(model.mail, model.id)) {
            log.warn("User with email: ${model.mail} already exists.")
            throw UniqueConstraintException("User with email: ${model.mail} already exists.")
        }
        if (userRepository.existsByPhoneNumberAndIdNot(model.phoneNumber, model.id)) {
            log.warn("User with phone number: ${model.phoneNumber} already exists.")
            throw UniqueConstraintException("User with phone number: ${model.phoneNumber} already exists.")
        }

        log.info("User with id ${model.id} successfully updated.")
        userRepository.save(model.toEntity())
        return SuccessResultModel()
    }

    fun getAllUsers() = userRepository.findAll().map { e -> e.toModel() }

    fun getPageUsers(model: RequestPageModel) = userRepository.findAll(model.toPageable()).map { e -> e.toModel() }

    @Throws(NotFoundException::class)
    fun delete(id: Long) {
        log.info("Try to delete user with id $id.")
        if (!userRepository.existsById(id)) {
            log.warn("User with id: $id not found.")
            throw NotFoundException("User with id: $id not found.")
        }
        userRepository.deleteById(id)
        log.info("User with id $id successfully deleted.")

    }

    fun UserModel.toEntity() = UserEntity(id, firstName, lastName, patronymic, birthDate, mail, sex, phoneNumber, password)

    fun UserEntity.toModel() = UserModel(id, firstName, lastName, patronymic, birthDate, eMail, sex, phoneNumber, password)

    fun RequestPageModel.toPageable() = if (sortedBy != null) {
        if (sortedType == SortedType.ASC)
            PageRequest.of(page, size, Sort.by(sortedBy).ascending())
        else
            PageRequest.of(page, size, Sort.by(sortedBy).descending())
    } else {
        PageRequest.of(page, size)
    }
}