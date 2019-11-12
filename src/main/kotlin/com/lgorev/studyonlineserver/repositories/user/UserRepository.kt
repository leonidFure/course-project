package com.lgorev.studyonlineserver.repositories.user

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
open interface UserRepository: PagingAndSortingRepository<UserEntity, Long>, MyUserRepository<UserEntity> {
    fun existsByeMail(eMail: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun existsByPhoneNumberAndIdNot(phoneNumber: String, id: Long): Boolean
    fun existsByeMailAndIdNot(eMail: String, id: Long): Boolean

}