package com.lgorev.studyonlineserver.api

import com.lgorev.studyonlineserver.services.TutorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tutors")
class TutorController (val tutorService: TutorService) {
    @GetMapping
    fun findAll() = tutorService.findAll()
}