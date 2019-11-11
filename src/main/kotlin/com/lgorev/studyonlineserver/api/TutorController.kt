package com.lgorev.studyonlineserver.api

import com.lgorev.studyonlineserver.domain.GeneralResultModel
import com.lgorev.studyonlineserver.domain.tutor.RequestTutorModel
import com.lgorev.studyonlineserver.services.TutorService
import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tutors")
class TutorController (val tutorService: TutorService) {
    @GetMapping
    fun findAll() = tutorService.findAll()

    @PostMapping
    fun save(@RequestBody model: RequestTutorModel): GeneralResultModel {
        val res = GeneralResultModel()
        try {
            tutorService.save(model)
        } catch (e: NotFoundException) {
            res.code = 2
            res.message = e.message?: "User not found"
        }
        return res
    }
}