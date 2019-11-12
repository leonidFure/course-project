package com.lgorev.studyonlineserver.repositories.user

interface MyUserRepository<T> {
    fun insert(entity: T): T
    fun findUserById(id: Long): T
}