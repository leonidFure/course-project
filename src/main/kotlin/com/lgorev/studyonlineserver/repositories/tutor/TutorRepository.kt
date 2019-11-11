package com.lgorev.studyonlineserver.repositories.tutor

import org.springframework.data.repository.PagingAndSortingRepository

interface TutorRepository: PagingAndSortingRepository<TutorEntity, Long> {
}