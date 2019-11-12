package com.lgorev.studyonlineserver.services

import com.lgorev.studyonlineserver.domain.GeneralResultModel
import com.lgorev.studyonlineserver.exceptions.NotFoundException
import com.lgorev.studyonlineserver.exceptions.UniqueConstraintException
import com.lgorev.studyonlineserver.domain.RequestPageModel
import com.lgorev.studyonlineserver.domain.SortedType
import com.lgorev.studyonlineserver.domain.SuccessResultModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.repositories.user.UserEntity
import com.lgorev.studyonlineserver.repositories.user.UserRepository
import org.apache.logging.log4j.LogManager
import org.hibernate.JDBCException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(noRollbackFor = [ConstraintViolationException::class])
@Service
open class UserService(private val userRepository: UserRepository) {

    private val log = LogManager.getLogger(UserService::class.java)
    private val regex = Regex("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$")

    fun register(model: UserModel): SuccessResultModel {
        log.info("Try to register user with eMail: ${model.mail}.")

        if(!regex.containsMatchIn(model.mail)) {
            log.warn("User email: ${model.mail} dont valid.")
            return SuccessResultModel(5, "User email: ${model.mail} dont valid.")
        }

        if (userRepository.existsByeMail(model.mail)) {
            log.warn("User with email: ${model.mail} already exists.")
            return SuccessResultModel(2, "User with email: ${model.mail} already exists.")
        }

        if (userRepository.existsByPhoneNumber(model.phoneNumber)) {
            log.warn("User with phone number: ${model.phoneNumber} already exists.")
            return SuccessResultModel(2, "User with phone number: ${model.phoneNumber} already exists.")
        }

        userRepository.save(model.toEntity())
        log.info("User with eMail ${model.mail} successfully registered.")

        return SuccessResultModel()
    }

    fun getUserById(id: Long): GeneralResultModel {
        val res = GeneralResultModel()
        res.code = 2
        res.message = "User with id: $id not found"

        userRepository.findById(id).ifPresent {
            res.userModel = it.toModel()
            res.code = 201
            res.message = "OK"
        }

        return res
    }

    fun update(model: UserModel): SuccessResultModel {
        log.info("Try to update user with id: ${model.id}.")

        if(!regex.containsMatchIn(model.mail)) {
            log.warn("User email: ${model.mail} dont valid.")
            return SuccessResultModel(5, "User email: ${model.mail} dont valid.")
        }

        if (userRepository.existsByeMailAndIdNot(model.mail, model.id)) {
            log.warn("User with email: ${model.mail} already exists.")
            return SuccessResultModel(2, "User with email: ${model.mail} already exists.")
        }

        if (userRepository.existsByPhoneNumberAndIdNot(model.phoneNumber, model.id)) {
            log.warn("User with phone number: ${model.phoneNumber} already exists.")
            return SuccessResultModel(2, "User with phone number: ${model.phoneNumber} already exists.")
        }

        if (!userRepository.existsById(model.id)) {
            log.info("User with id ${model.id} not found.")
            return SuccessResultModel(3, "User with id: ${model.id} not found.")
        }

        userRepository.save(model.toEntity())
        log.info("User with id ${model.id} successfully updated.")

        return SuccessResultModel()
    }

    fun getAllUsers() = userRepository.findAll().map { e -> e.toModel() }

    fun getPageUsers(model: RequestPageModel) = userRepository.findAll(model.toPageable()).map { e -> e.toModel() }

    fun delete(id: Long): SuccessResultModel {
        log.info("Try to delete user with id $id.")

        if (!userRepository.existsById(id)) {
            log.warn("User with id: $id not found.")
            return SuccessResultModel(3,"User with id: $id not found.")
        }

        userRepository.deleteById(id)
        log.info("User with id $id successfully deleted.")

        return SuccessResultModel()
    }

    fun testFind(id: Long) = userRepository.findUserById(id)

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