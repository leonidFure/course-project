package com.lgorev.studyonlineserver.repositories.user

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
open interface UserRepository: PagingAndSortingRepository<UserEntity, Long> {
    fun findByeMail(eMail: String): Optional<UserEntity>
    fun findByPhoneNumber(phoneNumber: String): Optional<UserEntity>
    fun existsByPhoneNumberAndIdNot(phoneNumber: String, id: Long): Boolean
    fun existsByeMailAndIdNot(eMail: String, id: Long): Boolean
}