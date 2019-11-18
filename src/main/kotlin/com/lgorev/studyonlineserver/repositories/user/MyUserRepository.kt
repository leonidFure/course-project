package com.lgorev.studyonlineserver.repositories.user

interface MyUserRepository<T> {
    fun insert(entity: T)
    fun findUserById(id: Long): T
    fun delete(id: Long)
    fun update(entity: T)
}