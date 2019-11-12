package com.lgorev.studyonlineserver.api

import com.lgorev.studyonlineserver.domain.RequestPageModel
import com.lgorev.studyonlineserver.domain.user.UserModel
import com.lgorev.studyonlineserver.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping("user")
    fun getUser(@RequestParam("id") id: Long) = userService.getUserById(id)

    @PostMapping
    fun register(@RequestBody model: UserModel) = userService.register(model)

    @PutMapping
    fun update(@RequestBody model: UserModel) = userService.update(model)

    @GetMapping("page")
    fun getPage(@RequestBody model: RequestPageModel) = userService.getPageUsers(model)

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long) = userService.delete(id)



    @GetMapping("test/{id}")
    fun testFind(@PathVariable("id") id: Long) = userService.testFind(id)
}
