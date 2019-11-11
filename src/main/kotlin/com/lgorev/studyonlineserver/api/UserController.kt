package com.lgorev.studyonlineserver.api

import com.lgorev.studyonlineserver.exceptions.NotFoundException
import com.lgorev.studyonlineserver.exceptions.UniqueConstraintException
import com.lgorev.studyonlineserver.domain.GeneralResultModel
import com.lgorev.studyonlineserver.domain.RequestPageModel
import com.lgorev.studyonlineserver.domain.SuccessResultModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping("user")
    fun getUser(@RequestParam("id") id: Long): GeneralResultModel {
        val res = GeneralResultModel()
        try {
            res.userModel = userService.getUserById(id)
        } catch (e: NotFoundException) {
            res.code = 2
            res.message = e.message ?: "User not found."
        }
        return res
    }

    @PostMapping
    fun register(@RequestBody model: UserModel): SuccessResultModel {
        val res = SuccessResultModel()
        try {
            userService.register(model)
        } catch (e: UniqueConstraintException) {
            res.code = 3
            res.message = e.message ?: "Unique constraint exception."
        }
        return res
    }

    @PutMapping
    fun update(@RequestBody model: UserModel): SuccessResultModel {
        val res = SuccessResultModel()
        try {
            userService.update(model)
        } catch (e: UniqueConstraintException) {
            res.code = 3
            res.message = e.message ?: "Unique constraint exception."
        }
        return res
    }

    @GetMapping("page")
    fun getPage(@RequestBody model: RequestPageModel) {
        userService.getPageUsers(model)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long) {
        val res = SuccessResultModel()
        try{
            userService.delete(id)
        } catch (e: NotFoundException) {
            res.code = 2
            res.message = e.message ?: "User not found."
        }
    }
}
