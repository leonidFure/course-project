package com.lgorev.studyonlineserver.repositories.user

import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.exceptions.UniqueConstraintException
import org.apache.logging.log4j.LogManager
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

open class MyUserRepositoryImpl(@PersistenceContext private val em: EntityManager) : MyUserRepository<UserEntity> {

    private val log = LogManager.getLogger(MyUserRepository::class)

    @Transactional(propagation = Propagation.MANDATORY)
    @Throws(UniqueConstraintException::class)
    override fun insert(entity: UserEntity): UserEntity {
        if(existsWithEmail(entity.eMail)) {
            log.warn("User with mail: ${entity.eMail} already exists.")
            throw UniqueConstraintException("User with mail: ${entity.eMail} already exists.")
        }

        if(existsWithPhoneNumber(entity.phoneNumber)) {
            log.warn("User with phone number: ${entity.phoneNumber} already exists.")
            throw UniqueConstraintException("User with phone number: ${entity.phoneNumber} already exists.")
        }
        TODO("Узнать как обрабатывать иключения возвращаемые триггерами СУБД;" +
                "Проработать внешние/первичные ключи у таблиц Пользователь, Ученик, Преподаватель;" +
                "Разобраться с FlyWay, попробовать подключить миграцию к проекту;" +
                "Разделить интерфейсы репозиториев не по сущностям а по кейсам использования;" +
                "Проработать триггреы для того что бы преподаватель не мог вести занятие у самого себя," +
                " не мог оставлять отзывы под своей страницей.")
    }

    override fun findUserById(id: Long): UserEntity = em.find(UserEntity::class.java, id)

    private fun existsWithEmail(mail: String): Boolean {
        val cb = em.criteriaBuilder
        val cq = cb.createQuery(UserEntity::class.java)
        val from = cq.from(UserEntity::class.java)
        cq.select(from)
        cq.where(cb.equal(from.get<String>(UserEntity.EMAIL), mail))
        return em.createQuery(cq).singleResult != null
    }

    private fun existsWithPhoneNumber(phoneNumber: String): Boolean {
        val cb = em.criteriaBuilder
        val cq = cb.createQuery(UserEntity::class.java)
        val from = cq.from(UserEntity::class.java)
        cq.select(from)
        cq.where(cb.equal(from.get<String>(UserEntity.PHONE_NUMBER), phoneNumber))
        return em.createQuery(cq).singleResult != null
    }


}